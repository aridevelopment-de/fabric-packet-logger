package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.ExperienceBarUpdateS2CPacket;

public class ExperienceBarUpdateS2CPacketHandler implements BasePacketHandler<ExperienceBarUpdateS2CPacket> {

    @Override
    public JsonObject serialize(ExperienceBarUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("experienceBar", packet.getBarProgress());
        jsonObject.addProperty("totalExperience", packet.getExperience());
        jsonObject.addProperty("level", packet.getExperienceLevel());
        return jsonObject;
    }
}
