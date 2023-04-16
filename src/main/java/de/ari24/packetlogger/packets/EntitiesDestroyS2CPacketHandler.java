package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.PacketLogger;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntitiesDestroyS2CPacket;

import java.util.ArrayList;
import java.util.List;

public class EntitiesDestroyS2CPacketHandler implements BasePacketHandler<EntitiesDestroyS2CPacket> {
    @Override
    public String name() {
        return "RemoveEntities";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Remove_Entities";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();;
        jsonObject.addProperty("general", "Sent by the server when an entity is to be destroyed on the client. ");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("entityIds", "The entity ids of the entities that are to be destroyed. ");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(EntitiesDestroyS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ClientWorld clientWorld = MinecraftClient.getInstance().world;

        if (PacketLogger.CONFIG.resolveEntityIdsToEntities() && clientWorld != null) {
            List<JsonObject> entities = new ArrayList<>();

            for (int entityId : packet.getEntityIds()) {
                Entity entity = clientWorld.getEntityById(entityId);

                if (entity != null) {
                    entities.add(ConvertUtils.serializeEntity(entity));
                }
            }

            jsonObject.addProperty("entityCount", entities.size());
            jsonObject.add("entities", ConvertUtils.GSON_INSTANCE.toJsonTree(entities));
        }

        jsonObject.add("entityIds", ConvertUtils.GSON_INSTANCE.toJsonTree(packet.getEntityIds()));
        return jsonObject;
    }
}
