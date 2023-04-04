package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntitySetHeadYawS2CPacket;

public class EntitySetHeadYawS2CPacketHandler implements BasePacketHandler<EntitySetHeadYawS2CPacket> {
    @Override
    public String id() {
        return "SetHeadRotation";
    }

    @Override
    public JsonObject serialize(EntitySetHeadYawS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ClientWorld world = MinecraftClient.getInstance().world;

        if (world != null) {
            Entity entity = packet.getEntity(world);

            if (entity != null) {
                jsonObject.add("entity", ConvertUtils.serializeEntity(entity));
            } else {
                jsonObject.addProperty("entityId", "unknown");
            }
        } else {
            jsonObject.addProperty("entityId", "unknown");
        }

        jsonObject.addProperty("headYaw", packet.getHeadYaw());
        return jsonObject;
    }
}
