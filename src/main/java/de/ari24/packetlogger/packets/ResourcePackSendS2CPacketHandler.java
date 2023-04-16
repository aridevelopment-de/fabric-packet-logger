package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.ResourcePackSendS2CPacket;

public class ResourcePackSendS2CPacketHandler implements BasePacketHandler<ResourcePackSendS2CPacket> {
    @Override
    public String name() {
        return "ResourcePack";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Resource_Pack";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet is sent by the server to request the client to download a resource pack. The client will, after manually confirming, download the resource pack and send a Resource Pack Status packet back to the server.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("url", "The URL of the resource pack Max chars: 32767.");
        jsonObject.addProperty("hash", "A 40 character hexadecimal and lowercase SHA-1 hash of the resource pack file. If it's not a 40 character hexadecimal string, the client will not use it for hash verification and likely waste bandwidth — but it will still treat it as a unique id ");
        jsonObject.addProperty("forced", "The notchian client will be forced to use the resource pack from the server. If they decline they will be kicked from the server. ");
        jsonObject.addProperty("promptMessage", "This is shown in the prompt making the client accept or decline the resource pack. ");
        return jsonObject;
    }

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
