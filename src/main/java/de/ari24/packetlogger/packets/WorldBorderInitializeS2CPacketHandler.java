package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.WorldBorderInitializeS2CPacket;

public class WorldBorderInitializeS2CPacketHandler implements BasePacketHandler<WorldBorderInitializeS2CPacket> {
    @Override
    public String name() {
        return "InitializeWorldBorder";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Initialize_World_Border";
    }

    @Override
    public JsonObject serialize(WorldBorderInitializeS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("centerX", packet.getCenterX());
        jsonObject.addProperty("centerZ", packet.getCenterZ());
        jsonObject.addProperty("size", packet.getSize());
        jsonObject.addProperty("sizeLerpTarget", packet.getSizeLerpTarget());
        jsonObject.addProperty("sizeLerpTime", packet.getSizeLerpTime());
        jsonObject.addProperty("maxRadius", packet.getMaxRadius());
        jsonObject.addProperty("warningBlocks", packet.getWarningBlocks());
        jsonObject.addProperty("warningTime", packet.getWarningTime());
        return jsonObject;
    }
}
