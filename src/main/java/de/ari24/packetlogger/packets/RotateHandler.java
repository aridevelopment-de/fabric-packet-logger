package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.EntityS2CPacket;

public class RotateHandler implements BasePacketHandler<EntityS2CPacket.Rotate> {
    @Override
    public String id() {
        return "UpdateEntityRotation";
    }

    @Override
    public JsonObject serialize(EntityS2CPacket.Rotate packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("entityId", "TODO");
        jsonObject.addProperty("yaw", packet.getYaw());
        jsonObject.addProperty("pitch", packet.getPitch());
        jsonObject.addProperty("onGround", packet.isOnGround());
        return jsonObject;
    }
}
