package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ParticleS2CPacketHandler implements BasePacketHandler<ParticleS2CPacket> {


    private <T extends ParticleEffect> JsonObject serializeParticleParameter(T particleEffect) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", particleEffect.getClass().getSimpleName());
        jsonObject.addProperty("data", particleEffect.asString());
        return jsonObject;
    }

    @Override
    public JsonObject serialize(ParticleS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();

        Identifier id = Registries.PARTICLE_TYPE.getId(packet.getParameters().getType());

        if (id != null) {
            jsonObject.addProperty("particleType", id.toString());
        } else {
            jsonObject.addProperty("particleType", packet.getParameters().getType().toString());
        }

        jsonObject.addProperty("longDistance", packet.isLongDistance());
        jsonObject.addProperty("x", packet.getX());
        jsonObject.addProperty("y", packet.getY());
        jsonObject.addProperty("z", packet.getZ());
        jsonObject.addProperty("offsetX", packet.getOffsetX());
        jsonObject.addProperty("offsetY", packet.getOffsetY());
        jsonObject.addProperty("offsetZ", packet.getOffsetZ());
        jsonObject.addProperty("maxSpeed", packet.getSpeed());
        jsonObject.addProperty("count", packet.getCount());
        jsonObject.add("parameters", serializeParticleParameter(packet.getParameters()));
        return jsonObject;
    }
}
