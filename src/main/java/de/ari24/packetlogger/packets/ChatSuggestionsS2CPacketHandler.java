package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.ChatSuggestionsS2CPacket;

public class ChatSuggestionsS2CPacketHandler implements BasePacketHandler<ChatSuggestionsS2CPacket> {
    @Override
    public String name() {
        return "ChatSuggestions";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Chat_Suggestions";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Unused by the Notchian server. Likely provided for custom servers to send chat message completions to clients.");
        jsonObject.add("wikiVg", JsonNull.INSTANCE);
        return jsonObject;
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
