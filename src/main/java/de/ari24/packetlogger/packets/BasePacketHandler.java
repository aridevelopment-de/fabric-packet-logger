package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import de.ari24.packetlogger.utils.ReflectionUtils;
import net.minecraft.network.packet.Packet;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public interface BasePacketHandler<T extends Packet<?>> {
    default @Nullable List<String> getJsonParameterOrder() {
        return null;
    }

    @SuppressWarnings("unchecked")
    default T deserialize(Class<T> clazz, JsonObject json) throws Exception {
        List<String> parameterOrder = getJsonParameterOrder();

        if (parameterOrder == null) {
            return null;
        }

        List<Object> parameters = new ArrayList<>();

        for (String parameter : parameterOrder) {
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
