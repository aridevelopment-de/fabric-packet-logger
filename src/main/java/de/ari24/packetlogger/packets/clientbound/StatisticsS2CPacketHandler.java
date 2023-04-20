package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.StatisticsS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class StatisticsS2CPacketHandler implements BasePacketHandler<StatisticsS2CPacket> {

    @Override
    public JsonObject serialize(StatisticsS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("count", packet.getStatMap().size());

        List<JsonObject> serializedStats = new ArrayList<>();

        packet.getStatMap().forEach((key, value) -> {
            JsonObject child = new JsonObject();
            Identifier identifier = Registries.STAT_TYPE.getId(key.getType());

            if (identifier == null) {
                child.addProperty("id", key.toString());
            } else {
                child.addProperty("id", identifier.toString());
            }

            child.addProperty("value", value);
            serializedStats.add(child);
        });

        jsonObject.add("stats", ConvertUtils.GSON_INSTANCE.toJsonTree(serializedStats));
        return jsonObject;
    }
}
