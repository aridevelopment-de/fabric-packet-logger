package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.ChunkRenderDistanceCenterS2CPacket;

public class ChunkRenderDistanceCenterS2CPacketHandler implements BasePacketHandler<ChunkRenderDistanceCenterS2CPacket> {
    @Override
    public String name() {
        return "SetCenterChunk";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Center_Chunk";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Updates the client's location. This is used to determine what chunks should remain loaded and if a chunk load should be ignored; chunks outside of the view distance may be unloaded. Sent whenever the player moves across a chunk border horizontally, and also (according to testing) for any integer change in the vertical axis, even if it doesn't go across a chunk section border. ");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("chunkX", "Chunk X coordinate of the player's position");
        jsonObject.addProperty("chunkZ", "Chunk Z coordinate of the player's position");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(ChunkRenderDistanceCenterS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("chunkX", packet.getChunkX());
        jsonObject.addProperty("chunkZ", packet.getChunkZ());
        return jsonObject;
    }
}
