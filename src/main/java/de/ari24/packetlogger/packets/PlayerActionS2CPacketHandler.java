package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;

public class PlayerActionS2CPacketHandler implements BasePacketHandler<PlayerActionC2SPacket> {
    @Override
    public String id() {
        return "PlayerAction";
    }

    @Override
    public JsonObject serialize(PlayerActionC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("pos", packet.getPos().toString());
        jsonObject.addProperty("direction", packet.getDirection().asString());
        jsonObject.addProperty("action", packet.getAction().name());
        jsonObject.addProperty("sequence", packet.getSequence());
        return jsonObject;
    }
}
