package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.WorldBorderInitializeS2CPacket;

public class WorldBorderInitializeS2CPacketHandler implements BasePacketHandler<WorldBorderInitializeS2CPacket> {

    @Override
    public JsonObject serialize(WorldBorderInitializeS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("x", packet.getCenterX());
        jsonObject.addProperty("z", packet.getCenterZ());
        jsonObject.addProperty("oldDiameter", packet.getSize());
        jsonObject.addProperty("newDiameter", packet.getMaxRadius());
        jsonObject.addProperty("speed", packet.getSizeLerpTime());
        jsonObject.addProperty("portalTeleportBoundary", packet.getSizeLerpTarget());
        jsonObject.addProperty("warningBlocks", packet.getWarningBlocks());
        jsonObject.addProperty("warningTime", packet.getWarningTime());
        return jsonObject;
    }
}
