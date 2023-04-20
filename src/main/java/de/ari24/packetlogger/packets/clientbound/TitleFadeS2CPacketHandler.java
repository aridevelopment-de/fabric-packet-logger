package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.TitleFadeS2CPacket;

public class TitleFadeS2CPacketHandler implements BasePacketHandler<TitleFadeS2CPacket> {
    @Override
    public String name() {
        return "SetTitleAnimationTimes";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Set_Title_Animation_Times";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Settings for the next/current title being displayed. Most likely being joined with the SetTitleText packet.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("fadeIn", "Ticks to spend fading in.");
        jsonObject.addProperty("stay", "Ticks to keep the title displayed.");
        jsonObject.addProperty("fadeOut", "Ticks to spend fading out, not when start fading out.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(TitleFadeS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("fadeIn", packet.getFadeInTicks());
        jsonObject.addProperty("stay", packet.getStayTicks());
        jsonObject.addProperty("fadeOut", packet.getFadeOutTicks());
        return jsonObject;
    }
}
