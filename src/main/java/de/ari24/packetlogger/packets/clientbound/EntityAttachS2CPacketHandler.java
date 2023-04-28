package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.EntityAttachS2CPacket;

public class EntityAttachS2CPacketHandler implements BasePacketHandler<EntityAttachS2CPacket> {

    @Override
    public JsonObject serialize(EntityAttachS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.getAttachedEntityId(), "attached", "attachedId");
        ConvertUtils.appendEntity(jsonObject, packet.getHoldingEntityId(), "holding", "holdingId");
        return jsonObject;
    }
}
