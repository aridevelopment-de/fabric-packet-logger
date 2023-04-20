package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.SignEditorOpenS2CPacket;

public class SignEditorOpenS2CPacketHandler implements BasePacketHandler<SignEditorOpenS2CPacket> {

    @Override
    public JsonObject serialize(SignEditorOpenS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("location", packet.getPos().toString());
        return jsonObject;
    }
}
