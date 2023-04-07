package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.CloseScreenS2CPacket;

public class CloseScreenS2CPacketHandler implements BasePacketHandler<CloseScreenS2CPacket> {
    @Override
    public String name() {
        return "CloseContainer";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Close_Container";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet is sent from the server to the client when a window is forcibly closed, such as when a chest is destroyed while it's open. The notchian client disregards the provided window ID and closes any active window. ");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("windowId", "This is the ID of the window that was closed. 0 for inventory.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(CloseScreenS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("windowId", packet.getSyncId());
        return jsonObject;
    }
}
