package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.BundleSplitterPacket;

public class BundleSplitterPacketHandler implements BasePacketHandler<BundleSplitterPacket<?>> {
    @Override
    public String name() {
        return "BundleDelimiter";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Bundle_Delimiter";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "The delimeter for a bundle of packets. When received, the client should store every subsequent packet it receives, and wait until another delimiter is received. Once that happens, the client is guaranteed to process every packet in the bundle on the same tick. The Notchian client doesn't allow more than 4096 packets in the same bundle.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        return jsonObject;
    }

    @Override
    public JsonObject serialize(BundleSplitterPacket<?> packet) {
        return new JsonObject();
    }
}
