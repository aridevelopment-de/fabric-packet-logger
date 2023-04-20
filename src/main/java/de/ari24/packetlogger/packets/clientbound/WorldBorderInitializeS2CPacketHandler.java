package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
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
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Initializes the world border (oh no, who could've seen this coming *screams in michael reeves*).");
        jsonObject.addProperty("wikiVgNotes", """
                The Notchian client determines how solid to display the warning by comparing to whichever is higher, the warning distance or whichever is lower, the distance from the current diameter to the target diameter or the place the border will be after warningTime seconds. In pseudocode:\s
               
               ```java
                distance = max(min(resizeSpeed * 1000 * warningTime, abs(targetDiameter - currentDiameter)), warningDistance);
                if (playerDistance < distance) {
                    warning = 1.0 - playerDistance / distance;
                } else {
                    warning = 0.0;
                }
                ```
                """);
        jsonObject.addProperty("x", "");
        jsonObject.addProperty("z", "");
        jsonObject.addProperty("oldDiameter", "Current length of a single side of the world border, in meters. ");
        jsonObject.addProperty("newDiameter", "Target length of a single side of the world border, in meters.");
        jsonObject.addProperty("speed", "Number of real-time milliseconds until New Diameter is reached. It appears that Notchian server does not sync world border speed to game ticks, so it gets out of sync with server lag. If the world border is not moving, this is set to 0");
        jsonObject.addProperty("portalTeleportBoundary", "Resulting coordinates from a portal teleport are limited to ±value. Usually 29999984. ");
        jsonObject.addProperty("warningBlocks", "If the player is within that range of blocks from the border, the hud will render a red vignette.");
        jsonObject.addProperty("warningTime", "In seconds as set by /worldborder warning time.");
        return jsonObject;
    }

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
