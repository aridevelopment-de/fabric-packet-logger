package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;

public class OverlayMessageS2CPacketHandler implements BasePacketHandler<OverlayMessageS2CPacket> {
    @Override
    public String name() {
        return "SetActionBarText";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Set_Action_Bar_Text";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sets the actionbar message. This will normally be visible for 60 ticks");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("message", "Displays a message above the hotbar (the same as position 2 in Player Chat Message). ");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(OverlayMessageS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("message", packet.getMessage().toString());
        return jsonObject;
    }
}
