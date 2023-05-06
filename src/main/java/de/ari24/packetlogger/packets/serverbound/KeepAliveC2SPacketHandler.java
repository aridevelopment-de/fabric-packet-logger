package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.KeepAliveC2SPacket;

public class KeepAliveC2SPacketHandler implements BasePacketHandler<KeepAliveC2SPacket> {
    @Override
    public JsonObject serialize(KeepAliveC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", packet.getId());
        return jsonObject;
    }
}
