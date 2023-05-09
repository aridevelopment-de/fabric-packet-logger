package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.MessageAcknowledgmentC2SPacket;

public class MessageAcknowledgmentC2SPacketHandler implements BasePacketHandler<MessageAcknowledgmentC2SPacket> {
    @Override
    public JsonObject serialize(MessageAcknowledgmentC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("count", packet.offset());
        return jsonObject;
    }
}
