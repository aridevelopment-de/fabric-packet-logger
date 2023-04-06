package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityS2CPacket;

public class MoveRelativeHandler implements BasePacketHandler<EntityS2CPacket.MoveRelative> {
    @Override
    public String name() {
        return "UpdateEntityPosition";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Update_Entity_Position";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet is sent by the server when an entity moves less then 8 blocks; if an entity moves more than 8 blocks Teleport Entity Packet should be sent instead.");
        jsonObject.addProperty("wikiVgNotes", "This packet allows at most 8 blocks movement in any direction, because short range is from -32768 to 32767. And 32768 / (128 * 32) = 8. ");
        jsonObject.addProperty("entityId", "The entity's ID.");
        jsonObject.addProperty("deltaX", "Change in X position as (currentX * 32 - prevX * 32) * 128");
        jsonObject.addProperty("deltaY", "Change in Y position as (currentY * 32 - prevY * 32) * 128");
        jsonObject.addProperty("deltaZ", "Change in Z position as (currentZ * 32 - prevZ * 32) * 128");
        jsonObject.addProperty("onGround", "True if the entity is on the ground.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(EntityS2CPacket.MoveRelative packet) {
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

        jsonObject.addProperty("deltaX", packet.getDeltaX());
        jsonObject.addProperty("deltaY", packet.getDeltaY());
        jsonObject.addProperty("deltaZ", packet.getDeltaZ());
        jsonObject.addProperty("onGround", packet.isOnGround());
        return jsonObject;
    }
}
