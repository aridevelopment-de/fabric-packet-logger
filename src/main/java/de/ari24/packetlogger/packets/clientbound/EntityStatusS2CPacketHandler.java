package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.EntityStatusS2CPacketAccessor;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;

public class EntityStatusS2CPacketHandler implements BasePacketHandler<EntityStatusS2CPacket> {

    @Override
    public EntityStatusS2CPacket deserialize(Class<EntityStatusS2CPacket> clazz, JsonObject json) throws Exception {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(json.get("entityId").getAsInt());
        buf.writeByte(json.get("status").getAsByte());
        return new EntityStatusS2CPacket(buf);
    }

    @Override
    public JsonObject serialize(EntityStatusS2CPacket packet) {
        EntityStatusS2CPacketAccessor accessor = (EntityStatusS2CPacketAccessor) packet;
        JsonObject jsonObject = new JsonObject();

        ConvertUtils.appendEntity(jsonObject, accessor.getId());
        jsonObject.addProperty("status", packet.getStatus());
        return jsonObject;
    }
}
