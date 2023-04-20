package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.login.LoginSuccessS2CPacket;

public class LoginSuccessS2CPacketHandler implements BasePacketHandler<LoginSuccessS2CPacket> {
    @Override
    public String name() {
        return "LoginSuccess";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Login_Success";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet switches the connection state to play. ");
        jsonObject.addProperty("wikiVgNotes", "The (notchian) server might take a bit to fully transition to the Play state, so it's recommended to wait for the Login (play) packet from the server.\bThe notchian client doesn't send any packets until the Login (play) packet (https://wiki.vg/Protocol#Login_.28play.29).");
        jsonObject.addProperty("gameProfile", "The game profile of the player who logged in.");
        ConvertUtils.appendGameProfileDescription(jsonObject);
        return jsonObject;
    }

    @Override
    public JsonObject serialize(LoginSuccessS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("gameProfile", ConvertUtils.serializeGameProfile(packet.getProfile()));
        return jsonObject;
    }
}
