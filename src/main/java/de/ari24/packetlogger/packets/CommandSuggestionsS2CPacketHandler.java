package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.CommandSuggestionsS2CPacket;

import java.util.ArrayList;
import java.util.List;

public class CommandSuggestionsS2CPacketHandler implements BasePacketHandler<CommandSuggestionsS2CPacket> {
    @Override
    public String name() {
        return "CommandSuggestionsResponse";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Command_Suggestions_Response";
    }

    @Override
    public JsonObject serialize(CommandSuggestionsS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("completionId", packet.getCompletionId());
        jsonObject.addProperty("start", packet.getSuggestions().getRange().getStart());
        jsonObject.addProperty("length", packet.getSuggestions().getRange().getLength());
        jsonObject.addProperty("count", packet.getSuggestions().getList().size());

        List<JsonObject> suggestions = new ArrayList<>();

        packet.getSuggestions().getList().forEach(suggestion -> {
            JsonObject child = new JsonObject();
            child.addProperty("match", suggestion.getText());
            child.addProperty("hasTooltip", suggestion.getTooltip() != null);

            if (suggestion.getTooltip() != null) {
                child.addProperty("tooltip", suggestion.getTooltip().getString());
            }
        });

        jsonObject.add("suggestions", ConvertUtils.GSON_INSTANCE.toJsonTree(suggestions));
        return jsonObject;
    }
}
