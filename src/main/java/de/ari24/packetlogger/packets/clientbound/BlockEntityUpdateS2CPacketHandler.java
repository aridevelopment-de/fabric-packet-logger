package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class BlockEntityUpdateS2CPacketHandler implements BasePacketHandler<BlockEntityUpdateS2CPacket> {
    @Override
    public JsonObject serialize(BlockEntityUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("location", packet.getPos().toString());

        Identifier id = null;

        if (packet.getBlockEntityType() != null) {
            id = Registries.BLOCK_ENTITY_TYPE.getId(packet.getBlockEntityType());
        }

        if (id != null) {
            jsonObject.addProperty("type", id.toString());
        } else {
            jsonObject.addProperty("type", packet.getBlockEntityType().toString());
        }

        if (packet.getNbt() == null) {
            jsonObject.add("nbt", JsonNull.INSTANCE);
        } else {
            jsonObject.addProperty("nbt", packet.getNbt().asString());
        }

        return jsonObject;
    }
}
