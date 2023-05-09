package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.LoginKeyC2SPacketAccessor;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.login.LoginKeyC2SPacket;

import java.util.Arrays;

public class LoginKeyC2SPacketHandler implements BasePacketHandler<LoginKeyC2SPacket> {
    @Override
    public JsonObject serialize(LoginKeyC2SPacket packet) {
        LoginKeyC2SPacketAccessor accessor = (LoginKeyC2SPacketAccessor) packet;

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("encryptedKeyLength", accessor.getEncryptedSecretKey().length);
        jsonObject.addProperty("encryptedSecretKey", Arrays.toString(accessor.getEncryptedSecretKey()));
        jsonObject.addProperty("verifyTokenLength", accessor.getNonce().length);
        jsonObject.addProperty("verifyToken", Arrays.toString(accessor.getNonce()));
        return jsonObject;
    }
}
