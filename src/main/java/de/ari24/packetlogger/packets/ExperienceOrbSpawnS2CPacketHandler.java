package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.ExperienceOrbSpawnS2CPacket;

public class ExperienceOrbSpawnS2CPacketHandler implements BasePacketHandler<ExperienceOrbSpawnS2CPacket> {
    @Override
    public String id() {
        return "SpawnExperienceOrb";
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
