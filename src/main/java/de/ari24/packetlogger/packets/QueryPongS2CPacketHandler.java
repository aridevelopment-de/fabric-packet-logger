package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.query.QueryPongS2CPacket;

public class QueryPongS2CPacketHandler implements BasePacketHandler<QueryPongS2CPacket> {
    @Override
    public String name() {
        return "PongPlay";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Pong_.28play.29";
    }

    @Override
    public JsonObject serialize(QueryPongS2CPacket packet) {
        JsonObject json = new JsonObject();
        json.addProperty("startTime", packet.getStartTime());
        return json;
    }
}
