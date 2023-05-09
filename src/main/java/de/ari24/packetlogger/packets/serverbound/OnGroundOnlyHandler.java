package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class OnGroundOnlyHandler implements BasePacketHandler<PlayerMoveC2SPacket.OnGroundOnly> {
    @Override
    public JsonObject serialize(PlayerMoveC2SPacket.OnGroundOnly packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("onGround", packet.isOnGround());
        return jsonObject;
    }
}
