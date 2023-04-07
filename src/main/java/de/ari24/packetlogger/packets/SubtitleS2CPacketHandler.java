package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.SubtitleS2CPacket;

public class SubtitleS2CPacketHandler implements BasePacketHandler<SubtitleS2CPacket> {
    @Override
    public String name() {
        return "SetSubtitleText";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Set_Subtitle_Text";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet is used to set the subtitle text of the player. It should only be shown if a title is currently being displayed.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("subtitleText", "The subtitle text to be displayed.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(SubtitleS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("subtitleText", packet.getSubtitle().toString());
        return jsonObject;
    }
}
