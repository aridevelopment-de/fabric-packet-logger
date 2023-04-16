package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityS2CPacket;

public class RotateAndMoveRelativeHandler implements BasePacketHandler<EntityS2CPacket.RotateAndMoveRelative> {
    @Override
    public String name() {
        return "UpdateEntityPositionAndRotation";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Update_Entity_Position_and_Rotation";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet is sent by the server when an entity rotates and moves. Since a short range is limited from -32768 to 32767, and movement is offset of fixed-point numbers, this packet allows at most 8 blocks movement in any direction. (-32768 / (32 * 128) == -8) ");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("entityId", "The entity's ID");
        jsonObject.addProperty("deltaX", "Change in X position as (currentX * 32 - prevX * 32) * 128");
        jsonObject.addProperty("deltaY", "Change in Y position as (currentY * 32 - prevY * 32) * 128");
        jsonObject.addProperty("deltaZ", "Change in Z position as (currentZ * 32 - prevZ * 32) * 128");
        jsonObject.addProperty("yaw", "The entity's new yaw, not a delta");
        jsonObject.addProperty("pitch", "The entity's new pitch, not a delta");
        jsonObject.addProperty("onGround", "Whether the entity is on the ground");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(EntityS2CPacket.RotateAndMoveRelative packet) {
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
        jsonObject.addProperty("yaw", packet.getYaw());
        jsonObject.addProperty("pitch", packet.getPitch());
        jsonObject.addProperty("onGround", packet.isOnGround());
        return jsonObject;
    }
}
