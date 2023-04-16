package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntitySetHeadYawS2CPacket;

public class EntitySetHeadYawS2CPacketHandler implements BasePacketHandler<EntitySetHeadYawS2CPacket> {
    @Override
    public String name() {
        return "SetHeadRotation";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Head_Rotation";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Changes the direction an entity's head is facing. While sending the Entity Look packet changes the vertical rotation of the head, sending this packet appears to be necessary to rotate the head horizontally.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("entityId", "The entity's ID.");
        jsonObject.addProperty("headYaw", "New angle, not a delta.");
        return jsonObject;
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
