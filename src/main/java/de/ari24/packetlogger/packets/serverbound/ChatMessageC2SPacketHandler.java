package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

import java.util.Arrays;

public class ChatMessageC2SPacketHandler implements BasePacketHandler<ChatMessageC2SPacket> {
    @Override
    public JsonObject serialize(ChatMessageC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("message", packet.chatMessage());
        jsonObject.addProperty("timestamp", packet.timestamp().toString());
        jsonObject.addProperty("salt", packet.salt());
        jsonObject.addProperty("hasSignature", packet.signature() != null);

        if (packet.signature() != null) {
            jsonObject.addProperty("signature", Arrays.toString(packet.signature().data()));
        }

        jsonObject.addProperty("messageCount", packet.acknowledgment().offset());
        jsonObject.addProperty("acknowledged", packet.acknowledgment().acknowledged().toString());
        return jsonObject;
    }
}
