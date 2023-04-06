package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import de.ari24.packetlogger.mixin.AdvancementProgressAccessor;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.network.packet.s2c.play.AdvancementUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Map;

public class AdvancementUpdateS2CPacketHandler implements BasePacketHandler<AdvancementUpdateS2CPacket> {
    @Override
    public String name() {
        return "UpdateAdvancements";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Update_Advancements";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "No description available.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("reset", "Whether to reset/clear the current advancements");
        jsonObject.addProperty("advancementMapping", "Advancements to earn");
        jsonObject.addProperty("toRemove", "Advancements to remove");
        jsonObject.addProperty("progressMapping", "Advancements to set progress");
        return jsonObject;
    }

    public static JsonObject serializeToEarn(Map<Identifier, Advancement.Builder> toEarn) {
        JsonObject jsonObject = new JsonObject();

        toEarn.forEach((id, builder) -> {
            try {
                jsonObject.add(id.toString(), builder.toJson());
            } catch (JsonSyntaxException ignore) {
                // Might be caused if the advancement criterion does not have any conditions
            }
        });

        return jsonObject;
    }

    public static JsonObject serializeToSetProgress(Map<Identifier, AdvancementProgress> toSetProgress) {
        JsonObject jsonObject = new JsonObject();

        toSetProgress.forEach((id, progress) -> {
            JsonObject child = new JsonObject();

            AdvancementProgressAccessor accessor = (AdvancementProgressAccessor) progress;
            accessor.getCriteriaProgresses().forEach((criterion, criterionProgress) -> {
                child.add(criterion, criterionProgress.toJson());
            });

            jsonObject.add(id.toString(), child);
        });

        return jsonObject;
    }

    @Override
    public JsonObject serialize(AdvancementUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("reset", packet.shouldClearCurrent());
        jsonObject.add("advancementMapping", serializeToEarn(packet.getAdvancementsToEarn()));
        jsonObject.add("toRemove", ConvertUtils.GSON_INSTANCE.toJsonTree(packet.getAdvancementIdsToRemove()));
        jsonObject.add("progressMapping", serializeToSetProgress(packet.getAdvancementsToProgress()));
        return jsonObject;
    }
}
