package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
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
    public JsonObject serialize(LoginSuccessS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("gameProfile", ConvertUtils.serializeGameProfile(packet.getProfile()));
        return jsonObject;
    }
}
