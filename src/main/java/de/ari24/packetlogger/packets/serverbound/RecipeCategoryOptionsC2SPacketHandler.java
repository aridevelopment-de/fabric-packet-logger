package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.RecipeCategoryOptionsC2SPacket;
import net.minecraft.recipe.book.RecipeBookCategory;

import java.util.Map;

public class RecipeCategoryOptionsC2SPacketHandler implements BasePacketHandler<RecipeCategoryOptionsC2SPacket> {
    private final Map<RecipeBookCategory, String> CATEGORY_MAP = Map.of(
            RecipeBookCategory.CRAFTING, "CRAFTING",
            RecipeBookCategory.FURNACE, "FURNACE",
            RecipeBookCategory.BLAST_FURNACE, "BLAST_FURNACE",
            RecipeBookCategory.SMOKER, "SMOKER"
    );

    @Override
    public JsonObject serialize(RecipeCategoryOptionsC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("category", CATEGORY_MAP.get(packet.getCategory()));
        jsonObject.addProperty("guiOpen", packet.isGuiOpen());
        jsonObject.addProperty("filterActive", packet.isFilteringCraftable());
        return jsonObject;
    }
}
