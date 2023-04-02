package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.EntityS2CPacket;

public class MoveRelativeHandler implements BasePacketHandler<EntityS2CPacket.MoveRelative> {
    @Override
    public String id() {
        return "UpdateEntityPosition";
    }

    @Override
    public JsonObject serialize(EntityS2CPacket.MoveRelative packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("entityId", "TODO");
        jsonObject.addProperty("deltaX", packet.getDeltaX());
        jsonObject.addProperty("deltaY", packet.getDeltaY());
        jsonObject.addProperty("deltaZ", packet.getDeltaZ());
        jsonObject.addProperty("onGround", packet.isOnGround());
        return jsonObject;
    }
}
