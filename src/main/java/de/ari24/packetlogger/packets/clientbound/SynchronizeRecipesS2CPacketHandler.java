package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.SynchronizeRecipesS2CPacket;
import net.minecraft.recipe.*;

import java.util.ArrayList;
import java.util.List;

public class SynchronizeRecipesS2CPacketHandler implements BasePacketHandler<SynchronizeRecipesS2CPacket> {

    public static JsonObject serializeRecipeData(Recipe<?> recipe) {
        JsonObject jsonObject = new JsonObject();

        if (recipe instanceof SpecialCraftingRecipe specialCraftingRecipe) {
            jsonObject.addProperty("category", specialCraftingRecipe.getCategory().asString());
        } else if (recipe instanceof SmithingTrimRecipe smithingTrimRecipe) {
            jsonObject.addProperty("template", "TODO");
            jsonObject.addProperty("base", "TODO");
            jsonObject.addProperty("addition", "TODO");
        } else if (recipe instanceof SmithingTransformRecipe smithingTransformRecipe) {
            jsonObject.addProperty("template", "TODO");
            jsonObject.addProperty("base", "TODO");
            jsonObject.addProperty("addition", "TODO");
            jsonObject.add("result", ConvertUtils.serializeItemStack(smithingTransformRecipe.getOutput(null)));
        } else if (recipe instanceof StonecuttingRecipe stonecuttingRecipe) {
            jsonObject.addProperty("group", stonecuttingRecipe.getGroup());
            jsonObject.add("ingredient", stonecuttingRecipe.getIngredients().get(0).toJson());
            jsonObject.add("result", ConvertUtils.serializeItemStack(stonecuttingRecipe.getOutput(null)));
        } else if (recipe instanceof AbstractCookingRecipe abstractCookingRecipe) {
            jsonObject.addProperty("group", abstractCookingRecipe.getGroup());
            jsonObject.addProperty("category", abstractCookingRecipe.getCategory().asString());
            jsonObject.add("ingredient", abstractCookingRecipe.getIngredients().get(0).toJson());
            jsonObject.add("result", ConvertUtils.serializeItemStack(abstractCookingRecipe.getOutput(null)));
            jsonObject.addProperty("experience", abstractCookingRecipe.getExperience());
            jsonObject.addProperty("cookingTime", abstractCookingRecipe.getCookTime());
        } else if (recipe instanceof ShapedRecipe shapedRecipe) {
            jsonObject.addProperty("width", shapedRecipe.getWidth());
            jsonObject.addProperty("height", shapedRecipe.getHeight());
            jsonObject.addProperty("group", shapedRecipe.getGroup());
            jsonObject.addProperty("category", shapedRecipe.getCategory().asString());

            List<JsonElement> ingredients = new ArrayList<>();
            shapedRecipe.getIngredients().forEach(ingredient -> ingredients.add(ingredient.toJson()));
            jsonObject.add("ingredients", ConvertUtils.GSON_INSTANCE.toJsonTree(ingredients));
            jsonObject.add("result", ConvertUtils.serializeItemStack(shapedRecipe.getOutput(null)));
            jsonObject.addProperty("showNotification", shapedRecipe.showNotification());
        } else if (recipe instanceof ShapelessRecipe shapelessRecipe) {
            jsonObject.addProperty("group", shapelessRecipe.getGroup());
            jsonObject.addProperty("category", shapelessRecipe.getCategory().asString());
            jsonObject.addProperty("ingredientCount", shapelessRecipe.getIngredients().size());

            List<JsonElement> ingredients = new ArrayList<>();
            shapelessRecipe.getIngredients().forEach(ingredient -> ingredients.add(ingredient.toJson()));
            jsonObject.add("ingredients", ConvertUtils.GSON_INSTANCE.toJsonTree(ingredients));
            jsonObject.add("result", ConvertUtils.serializeItemStack(shapelessRecipe.getOutput(null)));
        }

        return jsonObject;
    }

    @Override
    public JsonObject serialize(SynchronizeRecipesS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("numRecipes", packet.getRecipes().size());

        List<JsonObject> recipes = new ArrayList<>();

        for (Recipe<?> recipe : packet.getRecipes()) {
            JsonObject child = new JsonObject();
            child.addProperty("type", recipe.getType().toString());
            child.addProperty("id", recipe.getId().toString());
            child.add("data", serializeRecipeData(recipe));
            recipes.add(child);
        }

        jsonObject.add("recipes", ConvertUtils.GSON_INSTANCE.toJsonTree(recipes));
        return jsonObject;
    }
}
