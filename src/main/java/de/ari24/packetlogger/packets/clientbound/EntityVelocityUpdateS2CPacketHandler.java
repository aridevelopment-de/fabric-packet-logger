package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.util.math.Vec3d;

public class EntityVelocityUpdateS2CPacketHandler implements BasePacketHandler<EntityVelocityUpdateS2CPacket> {

    @Override
    public EntityVelocityUpdateS2CPacket deserialize(Class<EntityVelocityUpdateS2CPacket> clazz, JsonObject json) throws Exception {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeVarInt(json.get("entityId").getAsInt());
        buf.writeShort(json.get("velocityX").getAsShort());
        buf.writeShort(json.get("velocityY").getAsShort());
        buf.writeShort(json.get("velocityZ").getAsShort());
        return new EntityVelocityUpdateS2CPacket(buf);
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
