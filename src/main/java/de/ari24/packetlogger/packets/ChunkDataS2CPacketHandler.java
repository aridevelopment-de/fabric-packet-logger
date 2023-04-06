package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket;

public class ChunkDataS2CPacketHandler implements BasePacketHandler<ChunkDataS2CPacket> {
    @Override
    public String name() {
        return "ChunkDataAndLightUpdate";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Chunk_Data_and_Update_Light";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet sends all block entities in the chunk (though sending them is not required; it is still legal to send them with Block Entity Data later).");
        jsonObject.addProperty("wikiVgNotes", "The light data in this packet is the same format as in the Update Light packet.");
        jsonObject.addProperty("chunkX", "Chunk coordinate (block coordinate divided by 16, rounded down)");
        jsonObject.addProperty("chunkZ", "Chunk coordinate (block coordinate divided by 16, rounded down)");
        jsonObject.addProperty("chunkData", "The chunk data");
        jsonObject.addProperty("lightData", "The light data");
        return jsonObject;
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
