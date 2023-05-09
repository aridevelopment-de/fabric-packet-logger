package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.CraftFailedResponseS2CPacket;

public class CraftFailedResponseS2CPacketHandler implements BasePacketHandler<CraftFailedResponseS2CPacket> {
    @Override
    public JsonObject serialize(CraftFailedResponseS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("windowId", packet.getSyncId());
        jsonObject.addProperty("recipeId", packet.getRecipeId().toString());
        return jsonObject;
    }
}
