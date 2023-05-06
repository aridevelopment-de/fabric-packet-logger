package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.query.QueryPingC2SPacket;

public class QueryPingC2SPacketHandler implements BasePacketHandler<QueryPingC2SPacket> {
    @Override
    public JsonObject serialize(QueryPingC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("startTime", packet.getStartTime());
        return jsonObject;
    }
}
