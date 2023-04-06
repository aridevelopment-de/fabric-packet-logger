package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.PlayerActionResponseS2CPacket;

public class PlayerActionResponseS2CPacketHandler implements BasePacketHandler<PlayerActionResponseS2CPacket> {
    @Override
    public String name() {
        return "AcknowledgeBlockChange";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Acknowledge_Block_Change";
    }

    @Override
    public JsonObject serialize(PlayerActionResponseS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("sequence", packet.sequence());
        return jsonObject;
    }
}
