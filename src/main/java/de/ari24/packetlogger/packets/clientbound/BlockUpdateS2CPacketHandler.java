package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;

public class BlockUpdateS2CPacketHandler implements BasePacketHandler<BlockUpdateS2CPacket> {
    @Override
    public String name() {
        return "BlockUpdate";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Block_Update";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Fired whenever a block is changed within the render distance.");
        jsonObject.addProperty("wikiVgNotes", " Changing a block in a chunk that is not loaded is not a stable action. The Notchian client currently uses a shared empty chunk which is modified for all block changes in unloaded chunks; while in 1.9 this chunk never renders in older versions the changed block will appear in all copies of the empty chunk. Servers should avoid sending block changes in unloaded chunks and clients should ignore such packets.");
        jsonObject.addProperty("location", "Block Coordinates");
        jsonObject.addProperty("blockId", "The new block state ID for the block as given in the global palette. See https://minecraft.fandom.com/wiki/Data_values#Block_IDs for more information.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(BlockUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("location", packet.getPos().toString());
        jsonObject.addProperty("state", packet.getState().toString());
        return jsonObject;
    }
}
