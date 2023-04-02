package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket;

public class ChunkDataS2CPacketHandler implements BasePacketHandler<ChunkDataS2CPacket> {
    @Override
    public String id() {
        return "ChunkDataAndLightUpdate";
    }

    @Override
    public JsonObject serialize(ChunkDataS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("chunkX", packet.getX());
        jsonObject.addProperty("chunkZ", packet.getZ());
        jsonObject.add("chunkData", ConvertUtils.serializeChunkData(packet.getChunkData()));
        jsonObject.add("lightData", ConvertUtils.serializeLightData(packet.getLightData()));
        return jsonObject;
    }
}
