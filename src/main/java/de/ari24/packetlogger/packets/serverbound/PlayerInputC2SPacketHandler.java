package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.PlayerInputC2SPacket;

public class PlayerInputC2SPacketHandler implements BasePacketHandler<PlayerInputC2SPacket> {
    @Override
    public JsonObject serialize(PlayerInputC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("sideways", packet.getSideways());
        jsonObject.addProperty("forward", packet.getForward());
        jsonObject.addProperty("jumping", packet.isJumping());
        jsonObject.addProperty("sneaking", packet.isSneaking());
        return jsonObject;
    }
}
