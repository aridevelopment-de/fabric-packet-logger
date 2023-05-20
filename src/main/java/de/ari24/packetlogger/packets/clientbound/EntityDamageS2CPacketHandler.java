package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.EntityDamageS2CPacket;
import net.minecraft.util.math.Vec3d;

import java.util.Optional;

public class EntityDamageS2CPacketHandler implements BasePacketHandler<EntityDamageS2CPacket> {
    @Override
    public EntityDamageS2CPacket deserialize(Class<EntityDamageS2CPacket> clazz, JsonObject json) throws Exception {
        int entityId = json.get("entityId").getAsInt();
        int sourceTypeID = json.get("sourceTypeId").getAsInt();
        int sourceCauseID = json.get("sourceCauseId").getAsInt();
        int sourceDirectID = json.get("sourceDirectId").getAsInt();
        Optional<Vec3d> vec3d = Optional.empty();

        if (json.get("hasSourcePosition").getAsBoolean()) {
            vec3d = Optional.of(ConvertUtils.deserializeVec3d(json.get("sourcePosition").getAsString()));
        }

        return new EntityDamageS2CPacket(entityId, sourceTypeID, sourceCauseID, sourceDirectID, vec3d);
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
            jsonObject.addProperty("sourcePosition", ConvertUtils.serializeVec3d(packet.sourcePosition().get()));
        } else {
            jsonObject.add("sourcePosition", null);
        }

        return jsonObject;
    }
}
