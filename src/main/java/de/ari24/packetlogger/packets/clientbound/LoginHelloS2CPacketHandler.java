package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.LoginHelloS2CPacketAccessor;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.encryption.NetworkEncryptionException;
import net.minecraft.network.packet.s2c.login.LoginHelloS2CPacket;

public class LoginHelloS2CPacketHandler implements BasePacketHandler<LoginHelloS2CPacket> {

    @Override
    public JsonObject serialize(LoginHelloS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("serverId", packet.getServerId());

        try {
            jsonObject.add("publicKey", ConvertUtils.GSON_INSTANCE.toJsonTree(packet.getPublicKey().getEncoded()));
        } catch (NetworkEncryptionException exception) {
            jsonObject.addProperty("publicKey", "Could not decode encoded rsa public key");

            LoginHelloS2CPacketAccessor accessor = (LoginHelloS2CPacketAccessor) packet;
            jsonObject.add("rawPublicKey", ConvertUtils.GSON_INSTANCE.toJsonTree(accessor.getPublicKey()));
        }

        jsonObject.add("verifyToken", ConvertUtils.GSON_INSTANCE.toJsonTree(packet.getNonce()));
        return jsonObject;
    }
}
