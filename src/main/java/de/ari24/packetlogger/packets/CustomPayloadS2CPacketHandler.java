package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;

public class CustomPayloadS2CPacketHandler implements BasePacketHandler<CustomPayloadS2CPacket> {
    @Override
    public String id() {
        return "PluginMessage";
    }

    @Override
    public JsonObject serialize(CustomPayloadS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("channel", packet.getChannel().toString());
        jsonObject.addProperty("readableBytes", packet.getData().readableBytes());
        return jsonObject;
    }
}

