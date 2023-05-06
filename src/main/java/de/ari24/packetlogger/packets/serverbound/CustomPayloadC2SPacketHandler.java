package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;

public class CustomPayloadC2SPacketHandler implements BasePacketHandler<CustomPayloadC2SPacket> {
    @Override
    public JsonObject serialize(CustomPayloadC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("channel", packet.getChannel().toString());

        byte[] bytes = new byte[]{};
        packet.getData().resetReaderIndex();
        packet.getData().readBytes(bytes);
        packet.getData().resetReaderIndex();

        jsonObject.add("data", ConvertUtils.GSON_INSTANCE.toJsonTree(bytes));
        return jsonObject;
    }
}
