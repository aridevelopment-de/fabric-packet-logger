package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.SpectatorTeleportC2SPacketAccessor;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.SpectatorTeleportC2SPacket;

public class SpectatorTeleportC2SPacketHandler implements BasePacketHandler<SpectatorTeleportC2SPacket> {
    @Override
    public JsonObject serialize(SpectatorTeleportC2SPacket packet) {
        SpectatorTeleportC2SPacketAccessor accessor = (SpectatorTeleportC2SPacketAccessor) packet;

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid", accessor.getTargetUuid().toString());
        return jsonObject;
    }
}
