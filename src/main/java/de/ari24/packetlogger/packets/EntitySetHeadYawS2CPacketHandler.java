package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.EntitySetHeadYawS2CPacket;

public class EntitySetHeadYawS2CPacketHandler implements BasePacketHandler<EntitySetHeadYawS2CPacket> {
    @Override
    public String id() {
        return "SetHeadRotation";
    }

    @Override
    public JsonObject serialize(EntitySetHeadYawS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("entityId", "TODO");
        jsonObject.addProperty("headYaw", packet.getHeadYaw());
        return jsonObject;
    }
}
