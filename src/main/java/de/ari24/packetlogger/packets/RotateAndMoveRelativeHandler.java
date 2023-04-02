package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.EntityS2CPacket;

public class RotateAndMoveRelativeHandler implements BasePacketHandler<EntityS2CPacket.RotateAndMoveRelative> {
    @Override
    public String id() {
        return "UpdateEntityPositionAndRotation";
    }

    @Override
    public JsonObject serialize(EntityS2CPacket.RotateAndMoveRelative packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("entityId", "TODO");
        jsonObject.addProperty("deltaX", packet.getDeltaX());
        jsonObject.addProperty("deltaY", packet.getDeltaY());
        jsonObject.addProperty("deltaZ", packet.getDeltaZ());
        jsonObject.addProperty("yaw", packet.getYaw());
        jsonObject.addProperty("pitch", packet.getPitch());
        jsonObject.addProperty("onGround", packet.isOnGround());
        return jsonObject;
    }
}
