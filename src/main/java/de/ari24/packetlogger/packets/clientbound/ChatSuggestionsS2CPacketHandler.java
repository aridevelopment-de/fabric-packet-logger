package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.ChatSuggestionsS2CPacket;

import java.util.List;

public class ChatSuggestionsS2CPacketHandler implements BasePacketHandler<ChatSuggestionsS2CPacket> {
    @Override
    public ChatSuggestionsS2CPacket deserialize(Class<ChatSuggestionsS2CPacket> clazz, JsonObject json) throws Exception {
        ChatSuggestionsS2CPacket.Action action = ChatSuggestionsS2CPacket.Action.valueOf(json.get("action").getAsString());
        List<String> entries = json.get("entries").getAsJsonArray().asList().stream().map(JsonElement::getAsString).toList();
        return new ChatSuggestionsS2CPacket(action, entries);
    }

    @Override
    public JsonObject serialize(ChatSuggestionsS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", packet.action().toString());
        jsonObject.addProperty("count", packet.entries().size());
        jsonObject.add("entries", ConvertUtils.GSON_INSTANCE.toJsonTree(packet.entries()));
        return jsonObject;
    }
}
