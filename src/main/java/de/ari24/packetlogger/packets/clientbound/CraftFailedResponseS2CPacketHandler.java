package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.CraftFailedResponseS2CPacket;

public class CraftFailedResponseS2CPacketHandler implements BasePacketHandler<CraftFailedResponseS2CPacket> {
    @Override
    public String name() {
        return "PlaceGhostRecipe";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Place_Ghost_Recipe";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Response to the serverbound packet (Place Recipe), with the same recipe ID. Appears to be used to notify the UI. ");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("windowId", "");
        jsonObject.addProperty("recipeId", "");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(CraftFailedResponseS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("windowId", packet.getSyncId());
        jsonObject.addProperty("recipeId", packet.getRecipeId().toString());
        return jsonObject;
    }
}
