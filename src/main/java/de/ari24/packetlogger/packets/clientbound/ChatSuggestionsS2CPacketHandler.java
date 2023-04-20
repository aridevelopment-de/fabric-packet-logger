package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.ChatSuggestionsS2CPacket;

public class ChatSuggestionsS2CPacketHandler implements BasePacketHandler<ChatSuggestionsS2CPacket> {
    @Override
    public JsonObject serialize(ChatSuggestionsS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", packet.action().toString());
        jsonObject.addProperty("count", packet.entries().size());
        jsonObject.add("entries", ConvertUtils.GSON_INSTANCE.toJsonTree(packet.entries()));
        return jsonObject;
    }
}
