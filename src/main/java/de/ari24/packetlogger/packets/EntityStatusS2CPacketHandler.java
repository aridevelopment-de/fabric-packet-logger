package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;

public class EntityStatusS2CPacketHandler implements BasePacketHandler<EntityStatusS2CPacket> {
    @Override
    public String id() {
        return "SetEntityMetadata";
    }

    @Override
    public JsonObject serialize(EntityStatusS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        ClientWorld clientWorld = minecraftClient.world;

        Entity entity = packet.getEntity(clientWorld);

        if (entity == null) {
            jsonObject.addProperty("error", "entity not found");
            return jsonObject;
        }

        jsonObject.addProperty("entityId", entity.getId());
        jsonObject.addProperty("entityType", entity.getType().toString());
        jsonObject.addProperty("status", packet.getStatus());
        return jsonObject;
    }
}
