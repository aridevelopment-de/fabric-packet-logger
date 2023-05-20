package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.BlockEventS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class BlockEventS2CPacketHandler implements BasePacketHandler<BlockEventS2CPacket> {
    @Override
    public BlockEventS2CPacket deserialize(Class<BlockEventS2CPacket> clazz, JsonObject json) throws Exception {
        BlockPos pos = ConvertUtils.deserializeBlockPos(json.get("location").getAsString());
        String blockIdentifier = json.get("block").getAsString().replace("Block{", "").replace("}", "");
        Identifier identifier = Identifier.tryParse(blockIdentifier);

        if (identifier == null) {
            throw new Exception("Failed to parse block identifier: " + blockIdentifier);
        }

        int type = json.get("actionId").getAsInt();
        int data = json.get("actionParameter").getAsInt();
        return new BlockEventS2CPacket(pos, Registries.BLOCK.get(identifier), type, data);
    }

    @Override
    public JsonObject serialize(BlockEventS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("location", ConvertUtils.serializeBlockPos(packet.getPos()));
        jsonObject.addProperty("actionId", packet.getType());
        jsonObject.addProperty("actionParameter", packet.getData());
        jsonObject.addProperty("block", Registries.BLOCK.getId(packet.getBlock()).toString());
        return jsonObject;
    }
}
