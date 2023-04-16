package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.ProfilelessChatMessageS2CPacket;

public class ProfilelessChatMessageS2CPacketHandler implements BasePacketHandler<ProfilelessChatMessageS2CPacket> {

    @Override
    public String name() {
        return "DisguisedChatMessage";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Disguised_Chat_Message";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Used to send system chat messages to the client. Most minecraft servers do this.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("message", "");
        jsonObject.addProperty("chatType", "The chat type from the Login (play) packet used for this message.");
        jsonObject.addProperty("chatTypeName", "The name associated with the chat type. Usually the message sender's display name.");
        jsonObject.addProperty("hasTargetName", "");
        jsonObject.addProperty("targetName", "The target name associated with the chat type. Usually the message target's display name.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(ProfilelessChatMessageS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("message", packet.message().toString());
        jsonObject.addProperty("chatType", packet.chatType().typeId());
        jsonObject.addProperty("chatTypeName", packet.chatType().name().toString());
        jsonObject.addProperty("hasTargetName", packet.chatType().targetName() != null);

        if (packet.chatType().targetName() != null) {
            jsonObject.addProperty("targetName", packet.chatType().targetName().toString());
        }

        return jsonObject;
    }
}
