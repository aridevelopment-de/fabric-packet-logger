package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import de.ari24.packetlogger.utils.ReflectionUtils;
import net.minecraft.network.packet.Packet;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public interface BasePacketHandler<T extends Packet<?>> {
    List<String> getJsonParameterOrder();

    @SuppressWarnings("unchecked")
    default T deserialize(Class<T> clazz, JsonObject json) throws Exception {
        List<Object> parameters = new ArrayList<>();

        for (String parameter : getJsonParameterOrder()) {
            parameters.add(ConvertUtils.convertJsonPrimitive(json.get(parameter).getAsJsonPrimitive()));
        }

        Constructor<T> constructor = ReflectionUtils.findMatchingConstructor(clazz, parameters);

        if (constructor == null) {
            throw new NoSuchMethodException("No matching constructor found");
        }

        constructor.setAccessible(true);
        return constructor.newInstance(parameters.toArray());
    }

    JsonObject serialize(T packet) throws Exception;
}
