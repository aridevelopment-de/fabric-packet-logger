package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.SignEditorOpenS2CPacket;

public class SignEditorOpenS2CPacketHandler implements BasePacketHandler<SignEditorOpenS2CPacket> {
    @Override
    public String name() {
        return "OpenSignEditor";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Open_Sign_Editor";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sent when the client has placed a sign and is allowed to send Update Sign. There must already be a sign at the given location (which the client does not do automatically) - send a Block Update first. ");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("location", "The position of the sign");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(SignEditorOpenS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("location", packet.getPos().toString());
        return jsonObject;
    }
}
