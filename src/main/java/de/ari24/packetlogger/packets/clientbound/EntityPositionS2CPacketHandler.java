package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.EntityPositionS2CPacket;

public class EntityPositionS2CPacketHandler implements BasePacketHandler<EntityPositionS2CPacket> {
    @Override
    public EntityPositionS2CPacket deserialize(Class<EntityPositionS2CPacket> clazz, JsonObject json) throws Exception {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeVarInt(json.get("entityId").getAsInt());
        buf.writeDouble(json.get("x").getAsDouble());
        buf.writeDouble(json.get("y").getAsDouble());
        buf.writeDouble(json.get("z").getAsDouble());
        buf.writeByte(json.get("yaw").getAsByte());
        buf.writeByte(json.get("pitch").getAsByte());
        buf.writeBoolean(json.get("onGround").getAsBoolean());
        return new EntityPositionS2CPacket(buf);
    }

    @Override
    public JsonObject serialize(EntityPositionS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.getId());
        jsonObject.addProperty("x", packet.getX());
        jsonObject.addProperty("y", packet.getY());
        jsonObject.addProperty("z", packet.getZ());
        jsonObject.addProperty("yaw", packet.getYaw());
        jsonObject.addProperty("pitch", packet.getPitch());
        jsonObject.addProperty("onGround", packet.isOnGround());
        return jsonObject;
    }
}
