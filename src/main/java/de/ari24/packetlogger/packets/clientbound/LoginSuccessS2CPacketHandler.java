package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.login.LoginSuccessS2CPacket;

public class LoginSuccessS2CPacketHandler implements BasePacketHandler<LoginSuccessS2CPacket> {

    @Override
    public JsonObject serialize(LoginSuccessS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("gameProfile", ConvertUtils.serializeGameProfile(packet.getProfile()));
        return jsonObject;
    }
}
