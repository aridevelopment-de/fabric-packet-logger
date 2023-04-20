package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.ChunkDeltaS2CPacketAccessor;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.network.packet.s2c.play.ChunkDeltaUpdateS2CPacket;

import java.util.ArrayList;
import java.util.List;

public class ChunkDeltaUpdateS2CPacketHandler implements BasePacketHandler<ChunkDeltaUpdateS2CPacket> {
    @Override
    public JsonObject serialize(ChunkDeltaUpdateS2CPacket packet) {
        ChunkDeltaS2CPacketAccessor accessor = (ChunkDeltaS2CPacketAccessor) packet;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("sectionPos", accessor.getSectionPos().toString());
        jsonObject.addProperty("suppressLightUpdates", packet.shouldSkipLightingUpdates());
        jsonObject.addProperty("blockLength", accessor.getBlockStates().length);

        List<Long> blocks = new ArrayList<>();

        for (int i = 0; i < accessor.getBlockStates().length; i++) {
            BlockState blockState = accessor.getBlockStates()[i];
            blocks.add((long) Block.getRawIdFromState(blockState) << 12 | i);
        }

        jsonObject.add("blocks", ConvertUtils.GSON_INSTANCE.toJsonTree(blocks));
        return jsonObject;
    }
}
