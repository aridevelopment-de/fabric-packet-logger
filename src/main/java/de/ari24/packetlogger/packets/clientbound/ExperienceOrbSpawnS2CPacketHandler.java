package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.ExperienceOrbSpawnS2CPacket;

public class ExperienceOrbSpawnS2CPacketHandler implements BasePacketHandler<ExperienceOrbSpawnS2CPacket> {
    @Override
    public ExperienceOrbSpawnS2CPacket deserialize(Class<ExperienceOrbSpawnS2CPacket> clazz, JsonObject json) throws Exception {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeVarInt(json.get("entityId").getAsInt());
        buf.writeDouble(json.get("x").getAsDouble());
        buf.writeDouble(json.get("y").getAsDouble());
        buf.writeDouble(json.get("z").getAsDouble());
        buf.writeShort(json.get("count").getAsInt());
        return new ExperienceOrbSpawnS2CPacket(buf);
    }

    @Override
    public JsonObject serialize(ExperienceOrbSpawnS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.getId());
        jsonObject.addProperty("x", packet.getX());
        jsonObject.addProperty("y", packet.getY());
        jsonObject.addProperty("z", packet.getZ());
        jsonObject.addProperty("count", packet.getExperience());
        return jsonObject;
    }
}
