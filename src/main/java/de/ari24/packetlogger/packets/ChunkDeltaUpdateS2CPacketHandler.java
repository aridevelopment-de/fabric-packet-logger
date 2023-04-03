package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.ChunkDeltaS2CPacketAccessor;
import net.minecraft.network.packet.s2c.play.ChunkDeltaUpdateS2CPacket;

public class ChunkDeltaUpdateS2CPacketHandler implements BasePacketHandler<ChunkDeltaUpdateS2CPacket> {
    @Override
    public String id() {
        return "UpdateSectionBlocks";
    }

    @Override
    public JsonObject serialize(ChunkDeltaUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("sectionPos", ((ChunkDeltaS2CPacketAccessor) packet).getSectionPos().toShortString());
        jsonObject.addProperty("shouldSkipLightingUpdates", packet.shouldSkipLightingUpdates());
        jsonObject.addProperty("blockLength", ((ChunkDeltaS2CPacketAccessor) packet).getBlockStates().length);
        return jsonObject;
    }
}
