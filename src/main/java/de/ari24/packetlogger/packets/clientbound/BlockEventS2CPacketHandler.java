package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.BlockEventS2CPacket;

public class BlockEventS2CPacketHandler implements BasePacketHandler<BlockEventS2CPacket> {
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
