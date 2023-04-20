package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ParticleS2CPacketHandler implements BasePacketHandler<ParticleS2CPacket> {
    @Override
    public String name() {
        return "Particle";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Particle_2";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Displays the named particle");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("particleType", "The type of the particle. See https://wiki.vg/Protocol#Particle");
        jsonObject.addProperty("longDistance", "If true, particle view distance increases from 256 to 65536.");
        jsonObject.addProperty("x", "");
        jsonObject.addProperty("y", "");
        jsonObject.addProperty("z", "");
        jsonObject.addProperty("offsetX", "This is added to the X position after being multiplied by random.nextGaussian(). ");
        jsonObject.addProperty("offsetY", "This is added to the Y position after being multiplied by random.nextGaussian(). ");
        jsonObject.addProperty("offsetZ", "This is added to the Z position after being multiplied by random.nextGaussian(). ");
        jsonObject.addProperty("maxSpeed", "");
        jsonObject.addProperty("count", "The number of particles to create");
        jsonObject.addProperty("parameters", "The parameters of the particle. See https://wiki.vg/Protocol#Particle");
        return jsonObject;
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
