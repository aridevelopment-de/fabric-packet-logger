package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;

public class TitleS2CPacketHandler implements BasePacketHandler<TitleS2CPacket> {
    @Override
    public String name() {
        return "SetTitleText";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Set_Title_Text";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sets the title text. Title will most likely fade in & out when joined with the SetTitleAnimationTimes packet.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("title", "The title text to display.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(TitleS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("title", packet.getTitle().toString());
        return jsonObject;
    }
}
