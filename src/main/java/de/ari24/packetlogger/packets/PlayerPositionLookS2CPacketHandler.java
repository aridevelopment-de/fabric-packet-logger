package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;

public class PlayerPositionLookS2CPacketHandler implements BasePacketHandler<PlayerPositionLookS2CPacket> {
    @Override
    public String name() {
        return "SynchronizePlayerPosition";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Synchronize_Player_Position";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Updates the player's position on the server. This packet will also close the “Downloading Terrain” screen when joining/respawning. ");
        jsonObject.addProperty("wikiVgNotes", """
                   - If the distance between the last known position of the player on the server and the new position set by this packet is greater than 100 meters, the client will be kicked for “You moved too quickly :( (Hacking?)”.
                   - Also if the fixed-point number of X or Z is set greater than 3.2E7D the client will be kicked for “Illegal position”.
                   - Yaw is measured in degrees, and does not follow classical trigonometry rules. The unit circle of yaw on the XZ-plane starts at (0, 1) and turns counterclockwise, with 90 at (-1, 0), 180 at (0, -1) and 270 at (1, 0). Additionally, yaw is not clamped to between 0 and 360 degrees; any number is valid, including negative numbers and numbers greater than 360.
                   - Pitch is measured in degrees, where 0 is looking straight ahead, -90 is looking straight up, and 90 is looking straight down.
                   
                   About the Flags field:
                   <Dinnerbone> It's a bitfield, X/Y/Z/Y_ROT/X_ROT. If X is set, the x value is relative and not absolute.
                """);
        jsonObject.addProperty("x", "");
        jsonObject.addProperty("y", "");
        jsonObject.addProperty("z", "");
        jsonObject.addProperty("yaw", "Rotation on the x axis, in degrees");
        jsonObject.addProperty("pitch", "Rotation on the y axis, in degrees");
        jsonObject.addProperty("flags", "Bitfield, X/Y/Z/Y_ROT/X_ROT. If X is set, the x value is relative and not absolute.");
        jsonObject.addProperty("teleportId", "Client should confirm this packet with Confirm Teleportation Packet containing the same Teleport ID.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(PlayerPositionLookS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("x", packet.getX());
        jsonObject.addProperty("y", packet.getY());
        jsonObject.addProperty("z", packet.getZ());
        jsonObject.addProperty("yaw", packet.getYaw());
        jsonObject.addProperty("pitch", packet.getPitch());
        jsonObject.add("flags", ConvertUtils.GSON_INSTANCE.toJsonTree(packet.getFlags()));
        jsonObject.addProperty("teleportId", packet.getTeleportId());
        return jsonObject;
    }
}
