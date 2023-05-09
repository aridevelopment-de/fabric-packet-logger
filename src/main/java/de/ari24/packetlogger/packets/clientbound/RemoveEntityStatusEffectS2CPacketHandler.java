package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.RemoveEntityStatusEffectS2CPacket;

public class RemoveEntityStatusEffectS2CPacketHandler implements BasePacketHandler<RemoveEntityStatusEffectS2CPacket> {

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
