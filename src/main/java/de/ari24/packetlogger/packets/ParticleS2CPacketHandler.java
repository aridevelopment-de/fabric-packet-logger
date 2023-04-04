package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.particle.ParticleEffect;

public class ParticleS2CPacketHandler implements BasePacketHandler<ParticleS2CPacket> {
    @Override
    public String id() {
        return "Particle";
    }

    private <T extends ParticleEffect> JsonObject serializeParticleParameter(T particleEffect) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", particleEffect.getClass().getSimpleName());
        jsonObject.addProperty("data", particleEffect.asString());
        return jsonObject;
    }

    @Override
    public JsonObject serialize(ParticleS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("x", packet.getX());
        jsonObject.addProperty("y", packet.getY());
        jsonObject.addProperty("z", packet.getZ());
        jsonObject.addProperty("offsetX", packet.getOffsetX());
        jsonObject.addProperty("offsetY", packet.getOffsetY());
        jsonObject.addProperty("offsetZ", packet.getOffsetZ());
        jsonObject.addProperty("speed", packet.getSpeed());
        jsonObject.addProperty("count", packet.getCount());
        jsonObject.addProperty("longDistance", packet.isLongDistance());
        jsonObject.add("parameters", serializeParticleParameter(packet.getParameters()));
        return jsonObject;
    }
}
