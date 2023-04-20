package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.login.LoginDisconnectS2CPacket;

public class LoginDisconnectS2CPacketHandler implements BasePacketHandler<LoginDisconnectS2CPacket> {
    @Override
    public String name() {
        return "Disconnect (login)";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Disconnect_.28login.29";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sent by the server to decline connecting to the server.");
        jsonObject.addProperty("wikiVgNotes", "Servers use this most of the time when banning people.");
        jsonObject.addProperty("reason", "The reason why the player was disconnected");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(LoginDisconnectS2CPacket packet) {
        JsonObject jsonObjects = new JsonObject();
        jsonObjects.addProperty("reason", packet.getReason().getString());
        return jsonObjects;
    }
}
