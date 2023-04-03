package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;

public class WorldTimeUpdateS2CPacketHandler implements BasePacketHandler<WorldTimeUpdateS2CPacket> {
    @Override
    public String id() {
        return "UpdateTime";
    }

    @Override
    public JsonObject serialize(WorldTimeUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("time", packet.getTime());
        jsonObject.addProperty("timeOfDay", packet.getTimeOfDay());
        return jsonObject;
    }
}
