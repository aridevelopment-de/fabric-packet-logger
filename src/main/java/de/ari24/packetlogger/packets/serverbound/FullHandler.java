package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class FullHandler implements BasePacketHandler<PlayerMoveC2SPacket.Full> {
    @Override
    public JsonObject serialize(PlayerMoveC2SPacket.Full packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("x", packet.x);
        jsonObject.addProperty("y", packet.y);
        jsonObject.addProperty("z", packet.z);
        jsonObject.addProperty("yaw", packet.yaw);
        jsonObject.addProperty("pitch", packet.pitch);
        jsonObject.addProperty("onGround", packet.isOnGround());
        return jsonObject;
    }
}
