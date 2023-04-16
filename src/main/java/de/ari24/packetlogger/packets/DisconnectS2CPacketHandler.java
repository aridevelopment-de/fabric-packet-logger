package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.DisconnectS2CPacket;

public class DisconnectS2CPacketHandler implements BasePacketHandler<DisconnectS2CPacket> {
    @Override
    public String name() {
        return "Disconnect (play)";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Disconnect_.28play.29";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sent by the server before it disconnects a client. The client assumes that the server has already closed the connection by the time the packet arrives.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("reason", "Displayed to the client when the connection terminates");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(DisconnectS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("reason", packet.getReason().toString());
        return jsonObject;
    }
}
