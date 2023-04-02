package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;

public class BlockUpdateS2CPacketHandler implements BasePacketHandler<BlockUpdateS2CPacket> {
    @Override
    public String id() {
        return "BlockUpdate";
    }

    @Override
    public JsonObject serialize(BlockUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("pos", packet.getPos().toShortString());
        jsonObject.addProperty("state", packet.getState().toString());
        return jsonObject;
    }
}
