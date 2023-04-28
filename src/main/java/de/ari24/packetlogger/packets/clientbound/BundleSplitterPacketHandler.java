package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.BundleSplitterPacket;

public class BundleSplitterPacketHandler implements BasePacketHandler<BundleSplitterPacket<?>> {
    @Override
    public JsonObject serialize(BundleSplitterPacket<?> packet) {
        return new JsonObject();
    }
}
