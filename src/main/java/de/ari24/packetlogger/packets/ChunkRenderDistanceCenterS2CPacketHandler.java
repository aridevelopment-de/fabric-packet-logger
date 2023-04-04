package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.ChunkRenderDistanceCenterS2CPacket;

public class ChunkRenderDistanceCenterS2CPacketHandler implements BasePacketHandler<ChunkRenderDistanceCenterS2CPacket> {
    @Override
    public String id() {
        return "SetCenterChunk";
    }

    @Override
    public JsonObject serialize(ChunkRenderDistanceCenterS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("chunkX", packet.getChunkX());
        jsonObject.addProperty("chunkZ", packet.getChunkZ());
        return jsonObject;
    }
}
