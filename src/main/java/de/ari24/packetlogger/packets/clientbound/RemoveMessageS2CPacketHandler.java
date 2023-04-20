package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.RemoveMessageS2CPacket;

public class RemoveMessageS2CPacketHandler implements BasePacketHandler<RemoveMessageS2CPacket> {

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
