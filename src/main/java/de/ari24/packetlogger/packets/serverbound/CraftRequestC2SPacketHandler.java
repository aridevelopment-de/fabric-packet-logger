package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.CraftRequestC2SPacket;

public class CraftRequestC2SPacketHandler implements BasePacketHandler<CraftRequestC2SPacket> {
    @Override
    public JsonObject serialize(CraftRequestC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("windowId", packet.getSyncId());
        jsonObject.addProperty("recipe", packet.getRecipe().toString());
        jsonObject.addProperty("craftAll", packet.shouldCraftAll());
        return jsonObject;
    }
}
