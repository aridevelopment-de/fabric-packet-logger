package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.PlayerSessionC2SPacket;

import java.util.Arrays;

public class PlayerSessionC2SPacketHandler implements BasePacketHandler<PlayerSessionC2SPacket> {
    @Override
    public JsonObject serialize(PlayerSessionC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("sessionId", packet.chatSession().sessionId().toString());
        jsonObject.addProperty("expiresAt", packet.chatSession().publicKeyData().expiresAt().toString());
        jsonObject.addProperty("publicKey", Arrays.toString(packet.chatSession().publicKeyData().key().getEncoded()));
        jsonObject.addProperty("keySignature", Arrays.toString(packet.chatSession().publicKeyData().keySignature()));
        return jsonObject;
    }
}
