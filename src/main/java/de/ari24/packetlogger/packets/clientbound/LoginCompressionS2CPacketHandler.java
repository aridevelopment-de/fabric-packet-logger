package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.login.LoginCompressionS2CPacket;

public class LoginCompressionS2CPacketHandler implements BasePacketHandler<LoginCompressionS2CPacket> {
    @Override
    public String name() {
        return "SetCompression";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Set_Compression";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Enables compression. If compression is enabled, all following packets are encoded in the compressed packet format. Negative values will disable compression, meaning the packet format should remain in the uncompressed packet format. However, this packet is entirely optional, and if not sent, compression will also not be enabled (the notchian server does not send the packet when compression is disabled). ");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("threshold", "Maximum size of a packet before it is compressed");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(LoginCompressionS2CPacket packet) {
        JsonObject json = new JsonObject();
        json.addProperty("threshold", packet.getCompressionThreshold());
        return json;
    }
}
