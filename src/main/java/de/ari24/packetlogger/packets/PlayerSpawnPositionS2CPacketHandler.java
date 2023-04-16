package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.PlayerSpawnPositionS2CPacket;

public class PlayerSpawnPositionS2CPacketHandler implements BasePacketHandler<PlayerSpawnPositionS2CPacket> {
    @Override
    public String name() {
        return "SetDefaultSpawnPosition";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Default_Spawn_Position";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sent by the server after login to specify the coordinates of the spawn point (the point at which players spawn at, and which the compass points to). It can be sent at any time to update the point compasses point at. ");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("location", "Spawn Location");
        jsonObject.addProperty("angle", "The angle at which to respawn at.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(PlayerSpawnPositionS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("location", packet.getPos().toString());
        jsonObject.addProperty("angle", packet.getAngle());
        return jsonObject;
    }
}
