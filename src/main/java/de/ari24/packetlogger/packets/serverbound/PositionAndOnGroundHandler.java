package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class PositionAndOnGroundHandler implements BasePacketHandler<PlayerMoveC2SPacket.PositionAndOnGround> {
    @Override
    public JsonObject serialize(PlayerMoveC2SPacket.PositionAndOnGround packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("x", packet.x);
        jsonObject.addProperty("y", packet.y);
        jsonObject.addProperty("z", packet.z);
        jsonObject.addProperty("onGround", packet.isOnGround());
        return jsonObject;
    }
}
