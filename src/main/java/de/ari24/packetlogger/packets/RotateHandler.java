package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityS2CPacket;

public class RotateHandler implements BasePacketHandler<EntityS2CPacket.Rotate> {
    @Override
    public String name() {
        return "UpdateEntityRotation";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Update_Entity_Rotation";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet is sent by the server when an entity rotates");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("entityId", "The entity's ID");
        jsonObject.addProperty("yaw", "New angle, not a delta.");
        jsonObject.addProperty("pitch", "New angle, not a delta.");
        jsonObject.addProperty("onGround", "Whether the entity is on the ground");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(EntityS2CPacket.Rotate packet) {
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

        jsonObject.addProperty("yaw", packet.getYaw());
        jsonObject.addProperty("pitch", packet.getPitch());
        jsonObject.addProperty("onGround", packet.isOnGround());
        return jsonObject;
    }
}
