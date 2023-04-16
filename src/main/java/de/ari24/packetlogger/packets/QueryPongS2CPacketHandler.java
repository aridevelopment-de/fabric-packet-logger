package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.query.QueryPongS2CPacket;

public class QueryPongS2CPacketHandler implements BasePacketHandler<QueryPongS2CPacket> {
    @Override
    public String name() {
        return "PongPlay";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Pong_.28play.29";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Response to the clientbound packet (Ping) with the same id");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("id", "Id is the same as the ping packet");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(QueryPongS2CPacket packet) {
        JsonObject json = new JsonObject();
        json.addProperty("startTime", packet.getStartTime());
        return json;
    }
}
