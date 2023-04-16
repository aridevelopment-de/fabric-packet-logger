package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import de.ari24.packetlogger.utils.MinecraftUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;

public class EntitySpawnS2CPacketHandler implements BasePacketHandler<EntitySpawnS2CPacket> {
    private JsonObject serializeEntityData(int entityData, EntityType<?> entityType) {
        JsonObject jsonObject = new JsonObject();

        if (entityType == EntityType.ITEM_FRAME) {
            String facing = "";

            switch (entityData) {
                case 0 -> facing = "DOWN";
                case 1 -> facing = "UP";
                case 2 -> facing = "NORTH";
                case 3 -> facing = "SOUTH";
                case 4 -> facing = "WEST";
                case 5 -> facing = "EAST";
            }

            jsonObject.addProperty("itemFrameFacing", facing);
        } else if (entityType == EntityType.PAINTING) {
            String facing = "";

            switch (entityData) {
                case 2 -> facing = "NORTH";
                case 3 -> facing = "SOUTH";
                case 4 -> facing = "WEST";
                case 5 -> facing = "EAST";
            }

            jsonObject.addProperty("paintingFacing", facing);
        } else if (entityType == EntityType.FALLING_BLOCK) {
            jsonObject.addProperty("blockStateId", entityData);
        } else if (entityType == EntityType.FISHING_BOBBER) {
            jsonObject.addProperty("ownerId", entityData);
        } else if (MinecraftUtils.PROJECTILES.contains(entityType)) {
            jsonObject.addProperty("ownerId", entityData);
        } else if (entityType == EntityType.WARDEN) {
            jsonObject.addProperty("wardenEmergingPos", entityData == 1);
        } else {
            jsonObject.addProperty("unknown", entityData);
        }

        return jsonObject;
    }

    @Override
    public String name() {
        return "SpawnEntity";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Spawn_Entity";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sent by the server when a vehicle or other non-living entity is created.");
        jsonObject.addProperty("wikiVgNotes", "Velocity is believed to be in units of 1/8000 of a block per server tick (50ms); for example, -1343 would move (-1343 / 8000) = −0.167875 blocks per tick (or −3.3575 blocks per second). ");
        jsonObject.addProperty("entityId", "The entity's ID.");
        jsonObject.addProperty("uuid", "The entity's UUID.");
        jsonObject.addProperty("entityType", "The entity's type, see https://wiki.vg/Entity_metadata#Mobs");
        jsonObject.addProperty("x", "");
        jsonObject.addProperty("y", "");
        jsonObject.addProperty("z", "");
        jsonObject.addProperty("pitch", "To get the real pitch, you must divide this by (256.0F / 360.0F) ");
        jsonObject.addProperty("yaw", "To get the real yaw, you must divide this by (256.0F / 360.0F) ");
        jsonObject.addProperty("headYaw", "Only used by living entities, where the head of the entity may differ from the general body rotation. ");
        jsonObject.addProperty("entityData", "Meaning dependent on the entity type, see https://wiki.vg/Object_Data");
        jsonObject.addProperty("velocityX", "");
        jsonObject.addProperty("velocityY", "");
        jsonObject.addProperty("velocityZ", "");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(EntitySpawnS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.getId());
        jsonObject.addProperty("uuid", packet.getUuid().toString());
        jsonObject.addProperty("entityType", packet.getEntityType().getName().toString());
        jsonObject.addProperty("x", packet.getX());
        jsonObject.addProperty("y", packet.getY());
        jsonObject.addProperty("z", packet.getZ());
        jsonObject.addProperty("pitch", packet.getPitch());
        jsonObject.addProperty("yaw", packet.getYaw());
        jsonObject.addProperty("headYaw", packet.getHeadYaw());
        jsonObject.add("entityData", serializeEntityData(packet.getEntityData(), packet.getEntityType()));
        jsonObject.addProperty("velocityX", packet.getVelocityX());
        jsonObject.addProperty("velocityY", packet.getVelocityY());
        jsonObject.addProperty("velocityZ", packet.getVelocityZ());
        return jsonObject;
    }
}
