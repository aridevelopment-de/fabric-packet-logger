package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.ExperienceBarUpdateS2CPacket;

public class ExperienceBarUpdateS2CPacketHandler implements BasePacketHandler<ExperienceBarUpdateS2CPacket> {
    @Override
    public String name() {
        return "SetExperience";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Experience";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sent by the server when the client should change experience levels. ");
        jsonObject.addProperty("wikiVgNotes", "See https://minecraft.fandom.com/wiki/Experience#Leveling_up for total experience to level conversion");
        jsonObject.addProperty("experienceBar", "Between 0.0f and 1.0f");
        jsonObject.addProperty("totalExperience", "The total amount of experience the player has");
        jsonObject.addProperty("level", "The current level of the player");
        return jsonObject;
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
