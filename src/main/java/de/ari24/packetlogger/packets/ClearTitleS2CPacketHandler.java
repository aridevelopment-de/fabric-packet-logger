package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.ClearTitleS2CPacket;

public class ClearTitleS2CPacketHandler implements BasePacketHandler<ClearTitleS2CPacket> {
    @Override
    public String name() {
        return "ClearTitles";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Clear_Titles";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Clear the client's current title information, with the option to also reset it. ");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("reset", "If true, the next title will have a fade-in animation. If false, the fade-in ticks will be set to 0");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(ClearTitleS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("reset", packet.shouldReset());
        return jsonObject;
    }
}
