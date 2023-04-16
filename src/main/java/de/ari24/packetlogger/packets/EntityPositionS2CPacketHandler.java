package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.PacketLogger;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.EntityPositionS2CPacket;

public class EntityPositionS2CPacketHandler implements BasePacketHandler<EntityPositionS2CPacket> {

    @Override
    public String name() {
        return "TeleportEntity";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Teleport_Entity";
    }

    @Override
    public JsonObject serialize(EntityPositionS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.getId());
        jsonObject.addProperty("x", packet.getX());
        jsonObject.addProperty("y", packet.getY());
        jsonObject.addProperty("z", packet.getZ());
        jsonObject.addProperty("yaw", packet.getYaw());
        jsonObject.addProperty("pitch", packet.getPitch());
        jsonObject.addProperty("onGround", packet.isOnGround());
        return jsonObject;
    }
}
