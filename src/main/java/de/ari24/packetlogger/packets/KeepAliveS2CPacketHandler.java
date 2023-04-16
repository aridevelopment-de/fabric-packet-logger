package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.KeepAliveS2CPacket;

public class KeepAliveS2CPacketHandler implements BasePacketHandler<KeepAliveS2CPacket> {
    @Override
    public String name() {
        return "KeepAlive";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Keep_Alive";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "The server will frequently send out a keep-alive, each containing a random ID. The client must respond with the same payload (see serverbound Keep Alive). If the client does not respond to them for over 30 seconds, the server kicks the client. Vice versa, if the server does not send any keep-alives for 20 seconds, the client will disconnect and yields a \"Timed out\" exception.");
        jsonObject.addProperty("wikiVgNotes", "The Notchian server uses a system-dependent time in milliseconds to generate the keep alive ID value.");
        jsonObject.addProperty("keepAliveId", "The ID of the keep alive packet. This is a random number generated by the server.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(KeepAliveS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("keepAliveId", packet.getId());
        return jsonObject;
    }
}
