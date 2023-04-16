package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;

public class GameMessageS2CPacketHandler implements BasePacketHandler<GameMessageS2CPacket> {
    @Override
    public String name() {
        return "SystemChatMessage";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#System_Chat_Message";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sends system chat messages to client e.g. feedback from running a command, such as \"Your game mode has been updated to creative.\" ");
        jsonObject.addProperty("wikiVgNotes", "Identifying the difference between Chat/System Message is important as it helps respect the user's chat visibility options. See https://wiki.vg/Chat#Processing_chat for more info about these positions. ");
        jsonObject.addProperty("content", "The message to be displayed. Limited to 262144 bytes");
        jsonObject.addProperty("overlay", "Whether the message should be displayed in the actionbar.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(GameMessageS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("content", packet.content().toString());
        jsonObject.addProperty("overlay", packet.overlay());
        return jsonObject;
    }
}
