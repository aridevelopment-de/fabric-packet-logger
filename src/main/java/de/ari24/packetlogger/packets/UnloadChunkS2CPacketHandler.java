package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.UnloadChunkS2CPacket;

public class UnloadChunkS2CPacketHandler implements BasePacketHandler<UnloadChunkS2CPacket> {
    @Override
    public String id() {
        return "UnloadChunk";
    }

    @Override
    public JsonObject serialize(UnloadChunkS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("chunkX", packet.getX());
        jsonObject.addProperty("chunkZ", packet.getZ());
        return jsonObject;
    }
}
