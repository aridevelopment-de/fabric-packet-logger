package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.ServerMetadataS2CPacket;

public class ServerMetadataS2CPacketHandler implements BasePacketHandler<ServerMetadataS2CPacket> {
    @Override
    public String name() {
        return "ServerData";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Server_Data";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Server ping data");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("motd", "The server's MOTD");
        jsonObject.addProperty("favicon", "The server's favicon, PNG base64 string");
        jsonObject.addProperty("enforcesSecureChat", "Whether the server enforces secure chat");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(ServerMetadataS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("motd", packet.getDescription().toString());

        if (packet.getFavicon().isPresent()) {
            jsonObject.addProperty("favicon", new String(packet.getFavicon().get()));
        } else {
            jsonObject.add("favicon", JsonNull.INSTANCE);
        }

        jsonObject.addProperty("enforcesSecureChat", packet.isSecureChatEnforced());
        return jsonObject;
    }
}
