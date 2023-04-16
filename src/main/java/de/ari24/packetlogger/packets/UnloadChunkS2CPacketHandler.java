package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.UnloadChunkS2CPacket;

public class UnloadChunkS2CPacketHandler implements BasePacketHandler<UnloadChunkS2CPacket> {
    @Override
    public String name() {
        return "UnloadChunk";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Unload_Chunk";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Tells the client to unload a chunk column. ");
        jsonObject.addProperty("wikiVgNotes", "It is legal to send this packet even if the given chunk is not currently loaded. ");
        jsonObject.addProperty("chunkX", "Block coordinate divided by 16, rounded down");
        jsonObject.addProperty("chunkZ", "Block coordinate divided by 16, rounded down");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(UnloadChunkS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("chunkX", packet.getX());
        jsonObject.addProperty("chunkZ", packet.getZ());
        return jsonObject;
    }
}
