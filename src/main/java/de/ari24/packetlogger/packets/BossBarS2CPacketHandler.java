package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.BossBarS2CPacketAccessor;
import net.minecraft.network.packet.s2c.play.BossBarS2CPacket;

public class BossBarS2CPacketHandler implements BasePacketHandler<BossBarS2CPacket> {
    @Override
    public String name() {
        return "BossBar";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Boss_Bar";
    }

    @Override
    public JsonObject serialize(BossBarS2CPacket packet) {
        BossBarS2CPacketAccessor accessor = (BossBarS2CPacketAccessor) packet;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid", accessor.getUuid().toString());
        jsonObject.addProperty("action", "TODO");
        return jsonObject;
    }
}
