package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.PacketLogger;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityPassengersSetS2CPacket;

import java.util.ArrayList;
import java.util.List;

public class EntityPassengersSetS2CPacketHandler implements BasePacketHandler<EntityPassengersSetS2CPacket> {
    @Override
    public String name() {
        return "SetPassengers";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Passengers";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet is sent when an entity changes its passengers.");
        jsonObject.addProperty("entityId", "The vehicle's entity ID.");
        jsonObject.addProperty("passengerCount", "The amount of passengers.");
        jsonObject.addProperty("passengerIds", "The passenger's entity IDs.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(EntityPassengersSetS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ClientWorld clientWorld = MinecraftClient.getInstance().world;

        ConvertUtils.appendEntity(jsonObject, packet.getId());

        if (PacketLogger.CONFIG.resolveEntityIdsToEntities() && clientWorld != null) {
            List<JsonObject> entities = new ArrayList<>();

            for (int entityId : packet.getPassengerIds()) {
                Entity entity = clientWorld.getEntityById(entityId);

                if (entity != null) {
                    entities.add(ConvertUtils.serializeEntity(entity));
                }
            }

            jsonObject.addProperty("passengerCount", entities.size());
            jsonObject.add("passengers", ConvertUtils.GSON_INSTANCE.toJsonTree(entities));
        }

        jsonObject.add("passengerIds", ConvertUtils.GSON_INSTANCE.toJsonTree(packet.getPassengerIds()));
        return jsonObject;
    }
}
