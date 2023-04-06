package de.ari24.packetlogger.packets;

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
        return "https://wiki.vg/Protocol#Spawn_Entity";
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
