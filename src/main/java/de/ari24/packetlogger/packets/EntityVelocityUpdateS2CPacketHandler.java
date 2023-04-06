package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;

public class EntityVelocityUpdateS2CPacketHandler implements BasePacketHandler<EntityVelocityUpdateS2CPacket> {
    @Override
    public String name() {
        return "SetEntityVelocity";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Set_Entity_Velocity";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet is used to update the velocity of an entity.");
        jsonObject.addProperty("wikiVgNotes", "Velocity is believed to be in units of 1/8000 of a block per server tick (50ms); for example, -1343 would move (-1343 / 8000) = −0.167875 blocks per tick (or −3.3575 blocks per second). ");
        jsonObject.addProperty("velocityX", "The velocity on the X axis.");
        jsonObject.addProperty("velocityY", "The velocity on the Y axis.");
        jsonObject.addProperty("velocityZ", "The velocity on the Z axis.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(EntityVelocityUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.getId());
        jsonObject.addProperty("velocityX", packet.getVelocityX());
        jsonObject.addProperty("velocityY", packet.getVelocityY());
        jsonObject.addProperty("velocityZ", packet.getVelocityZ());
        return jsonObject;
    }
}
