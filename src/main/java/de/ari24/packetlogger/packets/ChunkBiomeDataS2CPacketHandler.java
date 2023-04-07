package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.ChunkBiomeDataS2CPacket;

import java.util.ArrayList;
import java.util.List;

public class ChunkBiomeDataS2CPacketHandler implements BasePacketHandler<ChunkBiomeDataS2CPacket> {
    @Override
    public String name() {
        return "ChunkBiomes";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Chunk_Biomes";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet updates chunk biomes for the chunks contained in the packet.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("numChunks", "Number of chunks");
        jsonObject.addProperty("chunks.pos", "Chunk position (block coordinate divided by 16, rounded down)");
        jsonObject.addProperty("chunks.size", "Size of data in bytes.");
        jsonObject.addProperty("chunk.buffer", "Chunk data structure, with sections containing the only Biomes field.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(ChunkBiomeDataS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("numChunks", packet.chunkBiomeData().size());

        List<JsonObject> serializedChunks = new ArrayList<>();
        packet.chunkBiomeData().forEach(serialized -> {
            JsonObject chunk = new JsonObject();
            chunk.addProperty("pos", serialized.pos().toString());
            chunk.addProperty("size", serialized.buffer().length);
            chunk.add("buffer", ConvertUtils.GSON_INSTANCE.toJsonTree(serialized.buffer()));
            serializedChunks.add(chunk);
        });

        jsonObject.add("chunks", ConvertUtils.GSON_INSTANCE.toJsonTree(serializedChunks));

        return jsonObject;
    }
}
