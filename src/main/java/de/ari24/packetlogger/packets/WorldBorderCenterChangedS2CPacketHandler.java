package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.WorldBorderCenterChangedS2CPacket;

public class WorldBorderCenterChangedS2CPacketHandler implements BasePacketHandler<WorldBorderCenterChangedS2CPacket> {
    @Override
    public String name() {
        return "SetBorderCenter";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Set_Border_Center";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sets the center of the world border.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("centerX", "The new center X coordinate.");
        jsonObject.addProperty("centerZ", "The new center Z coordinate.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(WorldBorderCenterChangedS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("centerX", packet.getCenterX());
        jsonObject.addProperty("centerZ", packet.getCenterZ());
        return jsonObject;
    }
}
