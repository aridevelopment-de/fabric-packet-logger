package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.ExperienceOrbSpawnS2CPacket;

public class ExperienceOrbSpawnS2CPacketHandler implements BasePacketHandler<ExperienceOrbSpawnS2CPacket> {
    @Override
    public String name() {
        return "SpawnExperienceOrb";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Spawn_Experience_Orb";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Spawns one or more experience orbs. ");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("x", "");
        jsonObject.addProperty("y", "");
        jsonObject.addProperty("z", "");
        jsonObject.addProperty("count", "The amount of experience this orb will reward once collected");
        return jsonObject;
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
