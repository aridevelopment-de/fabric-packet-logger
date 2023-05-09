package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;

public class CustomPayloadS2CPacketHandler implements BasePacketHandler<CustomPayloadS2CPacket> {
    @Override
    public JsonObject serialize(CustomPayloadS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("channel", packet.getChannel().toString());
        jsonObject.addProperty("readableBytes", packet.getData().readableBytes());

        byte[] bytes = new byte[]{};
        packet.getData().resetReaderIndex();
        packet.getData().readBytes(bytes);
        packet.getData().resetReaderIndex();
        jsonObject.add("data", ConvertUtils.GSON_INSTANCE.toJsonTree(bytes));
        return jsonObject;
    }
}

