package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.util.Identifier;

public class CustomPayloadS2CPacketHandler implements BasePacketHandler<CustomPayloadS2CPacket> {
    @Override
    public CustomPayloadS2CPacket deserialize(Class<CustomPayloadS2CPacket> clazz, JsonObject json) throws Exception {
        Identifier channel = Identifier.tryParse(json.get("channel").getAsString());

        if (channel == null) {
            throw new Exception("Invalid channel");
        }

        PacketByteBuf bytes = PacketByteBufs.create();
        bytes.writeBytes(ConvertUtils.GSON_INSTANCE.fromJson(json.get("data"), byte[].class));  // TODO: Test this
        return new CustomPayloadS2CPacket(channel, bytes);
    }

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

