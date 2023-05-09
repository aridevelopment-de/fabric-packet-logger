package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.ServerMetadataS2CPacket;

public class ServerMetadataS2CPacketHandler implements BasePacketHandler<ServerMetadataS2CPacket> {

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
