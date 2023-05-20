package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.ChunkRenderDistanceCenterS2CPacket;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChunkRenderDistanceCenterS2CPacketHandler implements BasePacketHandler<ChunkRenderDistanceCenterS2CPacket> {
    @Override
    public @Nullable List<String> getJsonParameterOrder() {
        return List.of("chunkX", "chunkZ");
    }

    @Override
    public JsonObject serialize(ChunkRenderDistanceCenterS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("chunkX", packet.getChunkX());
        jsonObject.addProperty("chunkZ", packet.getChunkZ());
        return jsonObject;
    }
}
