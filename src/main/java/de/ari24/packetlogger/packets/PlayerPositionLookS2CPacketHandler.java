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
        return "https://wiki.vg/Protocol#Synchronize_Player_Position";
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
