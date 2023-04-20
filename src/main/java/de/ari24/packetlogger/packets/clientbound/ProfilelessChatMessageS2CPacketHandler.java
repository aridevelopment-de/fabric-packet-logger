package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.ProfilelessChatMessageS2CPacket;

public class ProfilelessChatMessageS2CPacketHandler implements BasePacketHandler<ProfilelessChatMessageS2CPacket> {

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
