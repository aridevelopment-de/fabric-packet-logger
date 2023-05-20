package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.EntityAttachS2CPacket;

public class EntityAttachS2CPacketHandler implements BasePacketHandler<EntityAttachS2CPacket> {
    @Override
    public EntityAttachS2CPacket deserialize(Class<EntityAttachS2CPacket> clazz, JsonObject json) throws Exception {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(json.get("attachedId").getAsInt());
        buf.writeInt(json.get("holdingId").getAsInt());
        return new EntityAttachS2CPacket(buf);
    }

    @Override
    public JsonObject serialize(EntityAttachS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.getAttachedEntityId(), "attached", "attachedId");
        ConvertUtils.appendEntity(jsonObject, packet.getHoldingEntityId(), "holding", "holdingId");
        return jsonObject;
    }
}
