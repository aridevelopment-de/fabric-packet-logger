package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.ChatMessageS2CPacket;

public class ChatMessageS2CPacketHandler implements BasePacketHandler<ChatMessageS2CPacket> {
    @Override
    public String name() {
        return "PlayerChatMessage";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Player_Chat_Message";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sends the client a message from a player. ");
        jsonObject.addProperty("wikiVgNotes", "Currently a lot is unknown about this packet, blank descriptions are for those that are unknown");
        jsonObject.addProperty("sender", "Used by the Notchian client for the disableChat launch option. Setting both longs to 0 will always display the message regardless of the setting.");
        jsonObject.addProperty("index", "Probably being used by the Report feature. I could track it down to net.minecraft.client.report.log.ChatLog#add, TODO");
        jsonObject.addProperty("messageSignaturePresent", "States if a message signature is present.");
        jsonObject.addProperty("messageSignatureBytes", "Cryptography, the signature consists of the Sender UUID, Session UUID from the Player Session packet, Index, Salt, Timestamp in epoch seconds, the length of the original chat content, the original content itself, the length of Previous Messages, and all of the Previous message signatures. These values are hashed with SHA-256 and signed using the RSA cryptosystem. Modifying any of these values in the packet will cause this signature to fail. This buffer is always 256 bytes long. ");
        jsonObject.addProperty("message", "The message to be displayed. ");
        jsonObject.addProperty("timestamp", "Represents the time the message was signed as milliseconds since the epoch, used to check if the message was received within 2 minutes of it being sent. ");
        jsonObject.addProperty("salt", "Cryptography, used for validating the message signature");
        jsonObject.addProperty("unsignedContentPresent", "");
        jsonObject.addProperty("unsignedContent", "");
        jsonObject.addProperty("filterType", "If the message has been filtered.");
        jsonObject.addProperty("filterTypeBits", "");
        jsonObject.addProperty("chatType", "The chat type from the Login (play) packet used for this message.");
        jsonObject.addProperty("networkName", "");
        jsonObject.addProperty("networkTargetNamePresent", "");
        jsonObject.addProperty("networkTargetName", "");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(ChatMessageS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("sender", packet.sender().toString());
        jsonObject.addProperty("index", packet.index());
        jsonObject.addProperty("messageSignaturePresent", packet.signature() != null);

        if (packet.signature() != null) {
            jsonObject.add("messageSignatureBytes", ConvertUtils.GSON_INSTANCE.toJsonTree(packet.signature().data()));
        }

        jsonObject.addProperty("message", packet.body().content());
        jsonObject.addProperty("timestamp", packet.body().timestamp().toString());
        jsonObject.addProperty("salt", packet.body().salt());
        jsonObject.addProperty("unsignedContentPresent", packet.unsignedContent() != null);

        if (packet.unsignedContent() != null) {
            jsonObject.addProperty("unsignedContent", packet.unsignedContent().toString());
        }

        jsonObject.addProperty("filterType", "TODO");
        jsonObject.addProperty("filterIsFullyFiltered", packet.filterMask().isFullyFiltered());
        jsonObject.addProperty("filterIsPassThrough", packet.filterMask().isPassThrough());
        jsonObject.addProperty("filterTypeBits", "TODO");

        jsonObject.addProperty("chatType", packet.serializedParameters().typeId());
        jsonObject.addProperty("networkName", packet.serializedParameters().name().toString());
        jsonObject.addProperty("networkTargetNamePresent", packet.serializedParameters().targetName() != null);

        if (packet.serializedParameters().targetName() != null) {
            jsonObject.addProperty("networkTargetName", packet.serializedParameters().targetName().toString());
        }

        return jsonObject;
    }
}
