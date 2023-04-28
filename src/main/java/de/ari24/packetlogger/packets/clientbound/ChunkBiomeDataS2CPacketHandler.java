package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.ChunkBiomeDataS2CPacket;

import java.util.ArrayList;
import java.util.List;

public class ChunkBiomeDataS2CPacketHandler implements BasePacketHandler<ChunkBiomeDataS2CPacket> {
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
