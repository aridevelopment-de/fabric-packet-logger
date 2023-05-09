package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.ResourcePackSendS2CPacket;

public class ResourcePackSendS2CPacketHandler implements BasePacketHandler<ResourcePackSendS2CPacket> {

    @Override
    public JsonObject serialize(ResourcePackSendS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("url", packet.getURL());
        jsonObject.addProperty("hash", packet.getSHA1());
        jsonObject.addProperty("forced", packet.isRequired());
        jsonObject.addProperty("hasPromptMessages", packet.getPrompt() != null);

        if (packet.getPrompt() != null) {
            jsonObject.addProperty("promptMessage", packet.getPrompt().toString());
        }

        return jsonObject;
    }
}
