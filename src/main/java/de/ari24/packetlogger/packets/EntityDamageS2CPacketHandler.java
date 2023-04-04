package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.PacketLogger;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityDamageS2CPacket;

public class EntityDamageS2CPacketHandler implements BasePacketHandler<EntityDamageS2CPacket> {
    @Override
    public String id() {
        return "DamageEvent";
    }

    @Override
    public JsonObject serialize(EntityDamageS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();

        ConvertUtils.appendEntity(jsonObject, packet.entityId());
        jsonObject.addProperty("sourceTypeId", packet.sourceTypeId());
        jsonObject.addProperty("sourceCauseId", packet.sourceCauseId());
        jsonObject.addProperty("sourceDirectId", packet.sourceDirectId());

        if (packet.sourcePosition().isPresent()) {
            jsonObject.addProperty("sourcePosition", packet.sourcePosition().get().toString());
        }

        return jsonObject;
    }
}
