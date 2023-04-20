package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.RemoveEntityStatusEffectS2CPacket;

public class RemoveEntityStatusEffectS2CPacketHandler implements BasePacketHandler<RemoveEntityStatusEffectS2CPacket> {
    @Override
    public String name() {
        return "RemoveEntityEffect";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Remove_Entity_Effect";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet is sent to remove an effect (e.g. potion effect) from an entity.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("entityId", "The entity ID of the entity to remove the effect from.");
        jsonObject.addProperty("effect", "Details of the potion effect being added.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(RemoveEntityStatusEffectS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        ClientWorld clientWorld = minecraftClient.world;

        if (clientWorld != null) {
            Entity entity = packet.getEntity(clientWorld);

            if (entity == null) {
                jsonObject.addProperty("entityId", "unknown");
            } else {
                jsonObject.add("entity", ConvertUtils.serializeEntity(entity));
            }
        } else {
            jsonObject.addProperty("entityId", "unknown");
        }

        jsonObject.add("effect", ConvertUtils.serializeStatusEffect(packet.getEffectType()));
        return jsonObject;
    }
}
