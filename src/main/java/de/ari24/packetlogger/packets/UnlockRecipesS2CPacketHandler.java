package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.UnlockRecipesS2CPacket;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.book.RecipeBookOptions;

public class UnlockRecipesS2CPacketHandler implements BasePacketHandler<UnlockRecipesS2CPacket> {
    @Override
    public String id() {
        return "UpdateRecipeBook";
    }

    private JsonObject serializeOptions(RecipeBookOptions options) {
        JsonObject jsonObject = new JsonObject();

        for (RecipeBookCategory category : RecipeBookCategory.values()) {
            JsonObject child = new JsonObject();
            child.addProperty("isGuiOpen", options.isGuiOpen(category));
            child.addProperty("isFiltering", options.isFilteringCraftable(category));
            jsonObject.add(category.toString(), child);
        }

        return jsonObject;
    }

    @Override
    public JsonObject serialize(UnlockRecipesS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", packet.getAction().toString());
        jsonObject.add("recipeIdsToChange", ConvertUtils.GSON_INSTANCE.toJsonTree(packet.getRecipeIdsToChange()));
        jsonObject.add("recipeIdsToInit", ConvertUtils.GSON_INSTANCE.toJsonTree(packet.getRecipeIdsToInit()));
        jsonObject.add("options", serializeOptions(packet.getOptions()));
        return jsonObject;
    }
}
