package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.EntitySetHeadYawS2CPacketAccessor;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.EntitySetHeadYawS2CPacket;

public class EntitySetHeadYawS2CPacketHandler implements BasePacketHandler<EntitySetHeadYawS2CPacket> {

    @Override
    public EntitySetHeadYawS2CPacket deserialize(Class<EntitySetHeadYawS2CPacket> clazz, JsonObject json) throws Exception {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeVarInt(json.get("entityId").getAsInt());
        buf.writeByte(json.get("headYaw").getAsByte());
        return new EntitySetHeadYawS2CPacket(buf);
    }

    @Override
    public JsonObject serialize(EntitySetHeadYawS2CPacket packet) {
        EntitySetHeadYawS2CPacketAccessor accessor = (EntitySetHeadYawS2CPacketAccessor) packet;
        JsonObject jsonObject = new JsonObject();

        ConvertUtils.appendEntity(jsonObject, accessor.getEntity());
        jsonObject.addProperty("headYaw", packet.getHeadYaw());
        return jsonObject;
    }
}
