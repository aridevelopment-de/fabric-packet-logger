package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.EntityDamageS2CPacket;

public class EntityDamageS2CPacketHandler implements BasePacketHandler<EntityDamageS2CPacket> {


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
