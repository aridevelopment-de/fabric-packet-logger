package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.PlayerSpawnS2CPacket;

public class PlayerSpawnS2CPacketHandler implements BasePacketHandler<PlayerSpawnS2CPacket> {
    @Override
    public String name() {
        return "SpawnPlayer";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Spawn_Player";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet is sent by the server when a player comes into visible range, not when a player joins. This packet must be sent after the Player Info Update packet that adds the player data for the client to use when spawning a player. If the Player Info for the player spawned by this packet is not present when this packet arrives, Notchian clients will not spawn the player entity. The Player Info packet includes skin/cape data. Servers can, however, safely spawn player entities for players not in visible range. The client appears to handle it correctly.");
        jsonObject.addProperty("wikiVgNotes", """
                When in online mode, the UUIDs must be valid and have valid skin blobs. In offline mode, UUID v3 is used with the String OfflinePlayer:<player name>, encoded in UTF-8 (and case-sensitive). The Notchian server uses UUID.nameUUIDFromBytes, implemented by OpenJDK here.
                For NPCs UUID v2 should be used. Note:
                "<+Grum> i will never confirm this as a feature you know that :)"   
                In an example UUID, xxxxxxxx-xxxx-Yxxx-xxxx-xxxxxxxxxxxx, the UUID version is specified by Y. So, for UUID v3, Y will always be 3, and for UUID v2, Y will always be 2
                """);
        jsonObject.addProperty("entityId", "A unique integer ID mostly used in the protocol to identify the player.");
        jsonObject.addProperty("uuid", "See below for notes on offline mode and NPCs. ");
        jsonObject.addProperty("x", "");
        jsonObject.addProperty("y", "");
        jsonObject.addProperty("z", "");
        jsonObject.addProperty("yaw", "");
        jsonObject.addProperty("pitch", "");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(PlayerSpawnS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.getId());
        jsonObject.addProperty("uuid", packet.getPlayerUuid().toString());
        jsonObject.addProperty("x", packet.getX());
        jsonObject.addProperty("y", packet.getY());
        jsonObject.addProperty("z", packet.getZ());
        jsonObject.addProperty("yaw", packet.getYaw());
        jsonObject.addProperty("pitch", packet.getPitch());
        return jsonObject;
    }
}
