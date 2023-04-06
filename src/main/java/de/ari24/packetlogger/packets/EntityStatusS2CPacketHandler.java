package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;

public class EntityStatusS2CPacketHandler implements BasePacketHandler<EntityStatusS2CPacket> {
    @Override
    public String name() {
        return "SetEntityMetadata";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Set_Entity_Metadata";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Updates one or more metadata properties for an existing entity. Any properties not included in the Metadata field are left unchanged.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("entityId", "The entity's ID");
        jsonObject.addProperty("metadata", "The entity's metadata. See https://wiki.vg/Entity_metadata#Entity_Metadata_Format");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(EntityStatusS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        ClientWorld clientWorld = minecraftClient.world;

        if (clientWorld != null) {
            Entity entity = packet.getEntity(clientWorld);

            if (entity == null) {
                jsonObject.addProperty("entityId", "unknown");
            } else {
                jsonObject.add("entity", ConvertUtils.serializeEntity(entity));
            }
        } else {
            jsonObject.addProperty("entityId", "unknown");
        }

        jsonObject.addProperty("metadata", packet.getStatus());
        return jsonObject;
    }
}
