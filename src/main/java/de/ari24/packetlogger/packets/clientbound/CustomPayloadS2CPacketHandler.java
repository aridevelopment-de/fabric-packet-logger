package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;

public class CustomPayloadS2CPacketHandler implements BasePacketHandler<CustomPayloadS2CPacket> {
    @Override
    public String name() {
        return "PluginMessage";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Plugin_Message";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Mods and plugins can use this to send their data. Minecraft itself uses several plugin channels. These internal channels are in the minecraft namespace. More information on how it works on Dinnerbone's blog. More documentation about internal and popular registered channels are here (https://wiki.vg/Plugin_channels)");
        jsonObject.addProperty("wikiVgNotes", "In Notchian client, the maximum data length is 1048576 bytes. Dinnerbone's blog: https://dinnerbone.com/blog/2012/01/13/minecraft-plugin-channels-messaging/");
        jsonObject.addProperty("channel", "Name of the plugin channel used to send the data.");
        jsonObject.addProperty("readableBytes", "Length of the data in bytes.");
        jsonObject.addProperty("data", "Any data. The length of this array must be inferred from the packet length.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(CustomPayloadS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("channel", packet.getChannel().toString());
        jsonObject.addProperty("readableBytes", packet.getData().readableBytes());

        byte[] bytes = new byte[]{};
        packet.getData().resetReaderIndex();
        packet.getData().resetWriterIndex();
        packet.getData().readBytes(bytes);
        packet.getData().resetReaderIndex();
        packet.getData().resetWriterIndex();
        jsonObject.add("data", ConvertUtils.GSON_INSTANCE.toJsonTree(bytes));
        return jsonObject;
    }
}

