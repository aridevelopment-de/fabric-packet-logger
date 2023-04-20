package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.BossBarS2CPacketAccessor;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.BossBarS2CPacket;

public class BossBarS2CPacketHandler implements BasePacketHandler<BossBarS2CPacket> {
    @Override
    public JsonObject serialize(BossBarS2CPacket packet) {
        BossBarS2CPacketAccessor accessor = (BossBarS2CPacketAccessor) packet;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid", accessor.getUuid().toString());
        jsonObject.addProperty("action", "TODO");
        return jsonObject;
    }
}
