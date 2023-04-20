package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class BlockEntityUpdateS2CPacketHandler implements BasePacketHandler<BlockEntityUpdateS2CPacket> {
    @Override
    public String name() {
        return "BlockEntityData";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Block_Entity_Data";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sets the block entity associated with the block at the given location.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("location", "The location of the block entity");
        jsonObject.addProperty("type", "The type of the block entity");
        jsonObject.addProperty("nbt", "Data to set. May be a TAG_END (0), in which case the block entity at the given location is removed (though this is not required since the client will remove the block entity automatically on chunk unload or block removal).");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(BlockEntityUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("location", packet.getPos().toString());

        Identifier id = Registries.BLOCK_ENTITY_TYPE.getId(packet.getBlockEntityType());

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
