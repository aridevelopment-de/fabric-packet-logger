package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.BlockEventS2CPacket;

public class BlockEventS2CPacketHandler implements BasePacketHandler<BlockEventS2CPacket> {
    @Override
    public String name() {
        return "BlockAction";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Block_Action";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet is used for a number of actions and animations performed by blocks, usually non-persistent");
        jsonObject.addProperty("wikiVgNotes", "The client ignores the provided block type and instead uses the block state in their world. See https://wiki.vg/Block_Actions for a list of values.");
        jsonObject.addProperty("location", "The location of the block");
        jsonObject.addProperty("actionId", "Varies depending on the block -- see https://wiki.vg/Block_Actions");
        jsonObject.addProperty("actionParameter", "Varies depending on the block -- see https://wiki.vg/Block_Actions");
        jsonObject.addProperty("block", "The block type ID for the block. This value is unused by the Notchian client, as it will infer the type of block based on the given position. ");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(BlockEventS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("location", packet.getPos().toString());
        jsonObject.addProperty("actionId", packet.getType());
        jsonObject.addProperty("actionParameter", packet.getData());
        jsonObject.addProperty("block", packet.getBlock().toString());
        return jsonObject;
    }
}
