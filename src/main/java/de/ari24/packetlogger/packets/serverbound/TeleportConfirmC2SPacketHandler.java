package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.TeleportConfirmC2SPacket;

public class TeleportConfirmC2SPacketHandler implements BasePacketHandler<TeleportConfirmC2SPacket> {
    @Override
    public JsonObject serialize(TeleportConfirmC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("teleportId", packet.getTeleportId());
        return jsonObject;
    }
}
