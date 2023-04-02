package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;

public class EntityVelocityUpdateS2CPacketHandler implements BasePacketHandler<EntityVelocityUpdateS2CPacket> {
    @Override
    public String id() {
        return "SetEntityVelocity";
    }

    @Override
    public JsonObject serialize(EntityVelocityUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("entityId", packet.getId());
        jsonObject.addProperty("velocityX", packet.getVelocityX());
        jsonObject.addProperty("velocityY", packet.getVelocityY());
        jsonObject.addProperty("velocityZ", packet.getVelocityZ());
        return jsonObject;
    }
}
