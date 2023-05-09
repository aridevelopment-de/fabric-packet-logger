package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.BlockBreakingProgressS2CPacket;

public class BlockBreakingProgressS2CPacketHandler implements BasePacketHandler<BlockBreakingProgressS2CPacket> {
    @Override
    public JsonObject serialize(BlockBreakingProgressS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.getEntityId());
        jsonObject.addProperty("location", packet.getPos().toString());
        jsonObject.addProperty("destroyStage", packet.getProgress());
        return jsonObject;
    }
}
