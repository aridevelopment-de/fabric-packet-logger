package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.RemoveMessageS2CPacket;

public class RemoveMessageS2CPacketHandler implements BasePacketHandler<RemoveMessageS2CPacket> {
    @Override
    public String name() {
        return "DeleteMessage";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Delete_Message";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Removes a message from the client's chat. This only works for messages with signatures, system messages cannot be deleted with this packet. ");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("signatureLength", "Length of signature.");
        jsonObject.addProperty("signature", "Bytes of the signature");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(RemoveMessageS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("signatureLength", packet.messageSignature().id());

        if (packet.messageSignature().fullSignature() != null) {
            jsonObject.add("signature", ConvertUtils.GSON_INSTANCE.toJsonTree(packet.messageSignature().fullSignature().data()));
        }

        return jsonObject;
    }
}
