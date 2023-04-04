package de.ari24.packetlogger.packets;

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
    public String id() {
        return "RemoveEntities";
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
