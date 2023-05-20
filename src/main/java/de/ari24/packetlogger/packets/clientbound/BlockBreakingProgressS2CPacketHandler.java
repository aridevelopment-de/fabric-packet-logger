package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.BlockBreakingProgressS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockBreakingProgressS2CPacketHandler implements BasePacketHandler<BlockBreakingProgressS2CPacket> {
    @Override
    public BlockBreakingProgressS2CPacket deserialize(Class<BlockBreakingProgressS2CPacket> clazz, JsonObject json) throws Exception {
        int entityId = json.get("entityId").getAsInt();
        BlockPos pos = ConvertUtils.deserializeBlockPos(json.get("location").getAsString());
        int progress = json.get("destroyStage").getAsInt();

        return new BlockBreakingProgressS2CPacket(entityId, pos, progress);
    }

    @Override
    public JsonObject serialize(BlockBreakingProgressS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.getEntityId());
        jsonObject.addProperty("location", ConvertUtils.serializeBlockPos(packet.getPos()));
        jsonObject.addProperty("destroyStage", packet.getProgress());
        return jsonObject;
    }
}
