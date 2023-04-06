package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.PacketLogger;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityDamageS2CPacket;

public class EntityDamageS2CPacketHandler implements BasePacketHandler<EntityDamageS2CPacket> {
    @Override
    public String name() {
        return "DamageEvent";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Damage_Event";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet is sent when an entity is damaged by some source.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("entityId", "The entity id that was damaged.");
        jsonObject.addProperty("sourceTypeId", "The type id of the source that damaged the entity.");
        jsonObject.addProperty("sourceCauseId", "The ID + 1 of the entity responsible for the damage, if present. If not present, the value is 0");
        jsonObject.addProperty("sourceDirectId",
                """
                        The ID + 1 of the entity that directly dealt the damage, if present. If not present, the value is 0. If this field is present:
                        - and damage was dealt indirectly, such as by the use of a projectile, this field will contain the ID of such projectile;
                        - and damage was dealt directly, such as by manually attacking, this field will contain the same value as Source Cause ID."""
        );
        jsonObject.addProperty("hasSourcePosition", "The Notchian server sends the Source Position when the damage was dealt by the /damage command and a position was specified");
        jsonObject.addProperty("sourcePosition", "The position of the source that damaged the entity.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(EntityDamageS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();

        ConvertUtils.appendEntity(jsonObject, packet.entityId());
        jsonObject.addProperty("sourceTypeId", packet.sourceTypeId());
        jsonObject.addProperty("sourceCauseId", packet.sourceCauseId());
        jsonObject.addProperty("sourceDirectId", packet.sourceDirectId());
        jsonObject.addProperty("hasSourcePosition", packet.sourcePosition().isPresent());

        if (packet.sourcePosition().isPresent()) {
            jsonObject.addProperty("sourcePosition", packet.sourcePosition().get().toString());
        }

        return jsonObject;
    }
}
