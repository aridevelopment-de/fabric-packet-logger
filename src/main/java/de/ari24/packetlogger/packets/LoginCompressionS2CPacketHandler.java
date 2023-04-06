package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.login.LoginCompressionS2CPacket;

public class LoginCompressionS2CPacketHandler implements BasePacketHandler<LoginCompressionS2CPacket> {
    @Override
    public String name() {
        return "SetCompression";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Set_Compression";
    }

    @Override
    public JsonObject serialize(LoginCompressionS2CPacket packet) {
        JsonObject json = new JsonObject();
        json.addProperty("threshold", packet.getCompressionThreshold());
        return json;
    }
}
