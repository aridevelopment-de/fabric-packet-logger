package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.RecipeBookDataC2SPacket;

public class RecipeBookDataC2SPacketHandler implements BasePacketHandler<RecipeBookDataC2SPacket> {
    @Override
    public JsonObject serialize(RecipeBookDataC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("recipeId", packet.getRecipeId().toString());
        return jsonObject;
    }
}
