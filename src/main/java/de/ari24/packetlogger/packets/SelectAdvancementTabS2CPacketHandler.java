package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.SelectAdvancementTabS2CPacket;

public class SelectAdvancementTabS2CPacketHandler implements BasePacketHandler<SelectAdvancementTabS2CPacket> {
    @Override
    public String name() {
        return "SelectAdvancementTab";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Select_Advancements_Tab";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sent by the server to indicate that the client should switch advancement tab. Sent either when the client switches tab in the GUI or when an advancement in another tab is made. ");
        jsonObject.addProperty("wikiVgNotes", "If no or an invalid identifier is sent, the client will switch to the first tab in the GUI");
        jsonObject.addProperty("hasId", "");
        jsonObject.addProperty("tabId", "");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(SelectAdvancementTabS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("hasId", packet.getTabId() != null);

        if (packet.getTabId() != null) {
            jsonObject.addProperty("tabId", packet.getTabId().toString());
        }

        return jsonObject;
    }
}
