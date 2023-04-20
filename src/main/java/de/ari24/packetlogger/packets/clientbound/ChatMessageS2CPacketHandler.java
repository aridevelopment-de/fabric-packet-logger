package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.ChatMessageS2CPacket;

public class ChatMessageS2CPacketHandler implements BasePacketHandler<ChatMessageS2CPacket> {
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
