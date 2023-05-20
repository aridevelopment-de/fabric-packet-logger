package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.ExperienceBarUpdateS2CPacket;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExperienceBarUpdateS2CPacketHandler implements BasePacketHandler<ExperienceBarUpdateS2CPacket> {

    @Override
    public @Nullable List<String> getJsonParameterOrder() {
        return List.of("experienceBar", "level", "totalExperience");
    }

    @Override
    public JsonObject serialize(ExperienceBarUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("experienceBar", packet.getBarProgress());
        jsonObject.addProperty("totalExperience", packet.getExperience());
        jsonObject.addProperty("level", packet.getExperienceLevel());
        return jsonObject;
    }
}
