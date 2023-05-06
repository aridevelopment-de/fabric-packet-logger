package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class LookAndOnGroundHandler implements BasePacketHandler<PlayerMoveC2SPacket.LookAndOnGround> {
    @Override
    public JsonObject serialize(PlayerMoveC2SPacket.LookAndOnGround packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("yaw", packet.yaw);
        jsonObject.addProperty("pitch", packet.pitch);
        jsonObject.addProperty("onGround", packet.isOnGround());
        return jsonObject;
    }
}
