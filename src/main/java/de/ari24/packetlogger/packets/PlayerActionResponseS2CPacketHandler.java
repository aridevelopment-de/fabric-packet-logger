package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.PlayerActionResponseS2CPacket;

public class PlayerActionResponseS2CPacketHandler implements BasePacketHandler<PlayerActionResponseS2CPacket> {
    @Override
    public String id() {
        return "PlayerActionResponse?";
    }

    @Override
    public JsonObject serialize(PlayerActionResponseS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("sequence", packet.sequence());
        return jsonObject;
    }
}
