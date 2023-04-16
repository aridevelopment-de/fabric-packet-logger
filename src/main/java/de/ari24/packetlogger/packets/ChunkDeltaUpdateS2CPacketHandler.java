package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.ChunkDeltaS2CPacketAccessor;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.network.packet.s2c.play.ChunkDeltaUpdateS2CPacket;

import java.util.ArrayList;
import java.util.List;

public class ChunkDeltaUpdateS2CPacketHandler implements BasePacketHandler<ChunkDeltaUpdateS2CPacket> {
    @Override
    public String name() {
        return "UpdateSectionBlocks";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Update_Section_Blocks";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Fired whenever 2 or more blocks are changed within the same chunk on the same tick.");
        jsonObject.addProperty("wikiVgNotes", "Changing blocks in chunks not loaded by the client is unsafe (see note on htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Block_Update).");
        jsonObject.addProperty("sectionPos", "Chunk section coordinate (encoded chunk x and z with each 22 bits, and section y with 20 bits, from left to right).");
        jsonObject.addProperty("suppressLightUpdates", "Whether to ignore light updates caused by the contained changes. Always inverse the preceding Update Light packet's \"Trust Edges\" boolean");
        jsonObject.addProperty("blockLength", "Length of the block states array.");
        jsonObject.addProperty("blocks", "Each entry is composed of the block state id, shifted left by 12, and the relative block position in the chunk section (4 bits for x, z, and y, from left to right). ");
        return jsonObject;
    }

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
