package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.PlayPingS2CPacket;

public class PlayPingS2CPacketHandler implements BasePacketHandler<PlayPingS2CPacket> {

    @Override
    public String name() {
        return "Ping (play)";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Ping_.28play.29";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Packet is not used by the Notchian server. When sent to the client, client responds with a Pong packet with the same id. ");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("id", "");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(PlayPingS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", packet.getParameter());
        return jsonObject;
    }
}
