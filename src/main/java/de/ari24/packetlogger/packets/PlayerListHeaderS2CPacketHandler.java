package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.PlayerListHeaderS2CPacket;

public class PlayerListHeaderS2CPacketHandler implements BasePacketHandler<PlayerListHeaderS2CPacket> {
    @Override
    public String name() {
        return "SetTabListHeaderAndFooter";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Tab_List_Header_And_Footer";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet may be used by custom servers to display additional information above/below the player list. It is never sent by the Notchian server.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("header", "To remove the header, send a empty text component: {\"text\":\"\"}.");
        jsonObject.addProperty("footer", "To remove the footer, send a empty text component: {\"text\":\"\"}.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(PlayerListHeaderS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("header", packet.getHeader().getString());
        jsonObject.addProperty("footer", packet.getFooter().getString());
        return jsonObject;
    }
}
