package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;

public class BlockUpdateS2CPacketHandler implements BasePacketHandler<BlockUpdateS2CPacket> {
    @Override
    public JsonObject serialize(BlockUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("location", packet.getPos().toString());
        jsonObject.addProperty("state", packet.getState().toString());
        return jsonObject;
    }
}
