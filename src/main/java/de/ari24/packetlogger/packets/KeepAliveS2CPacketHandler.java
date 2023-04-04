package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.KeepAliveS2CPacket;

public class KeepAliveS2CPacketHandler implements BasePacketHandler<KeepAliveS2CPacket> {
    @Override
    public String id() {
        return "KeepAlive";
    }

    @Override
    public JsonObject serialize(KeepAliveS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", packet.getId());
        return jsonObject;
    }
}
