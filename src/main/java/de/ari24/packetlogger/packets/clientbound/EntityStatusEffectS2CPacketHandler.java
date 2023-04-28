package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.EntityStatusEffectS2CPacket;

public class EntityStatusEffectS2CPacketHandler implements BasePacketHandler<EntityStatusEffectS2CPacket> {
    @Override
    public JsonObject serialize(EntityStatusEffectS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.getEntityId());
        jsonObject.add("effect", ConvertUtils.serializeStatusEffect(packet.getEffectId()));
        jsonObject.addProperty("amplifier", packet.getAmplifier());
        jsonObject.addProperty("duration", packet.getDuration());
        jsonObject.addProperty("showParticles", packet.shouldShowParticles());
        jsonObject.addProperty("isAmbient", packet.isAmbient());
        jsonObject.addProperty("showIcon", packet.shouldShowIcon());
        jsonObject.addProperty("hasFactorData", packet.getFactorCalculationData() != null);
        jsonObject.addProperty("factorCoded", "TODO");
        return jsonObject;
    }
}
