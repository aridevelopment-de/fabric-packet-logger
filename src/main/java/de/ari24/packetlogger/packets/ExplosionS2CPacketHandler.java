package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;

public class ExplosionS2CPacketHandler implements BasePacketHandler<ExplosionS2CPacket> {
    @Override
    public String name() {
        return "Explosion";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Explosion";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sent when an explosion occurs (creepers, TNT, and ghast fireballs). Each block in Records is set to air. Coordinates for each axis in record is int(X) + record.x ");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("x", "");
        jsonObject.addProperty("y", "");
        jsonObject.addProperty("z", "");
        jsonObject.addProperty("strength", "A strength greater than or equal to 2.0 spawns a minecraft:explosion_emitter particle, while a lesser strength spawns a minecraft:explosion particle. This field is also used as the radius.");
        jsonObject.addProperty("recordCount", "");
        jsonObject.addProperty("records", "Each record is 3 signed bytes long; the 3 bytes are the XYZ (respectively) signed offsets of affected blocks. ");
        jsonObject.addProperty("playerMotionX", "X velocity of the player being pushed by the explosion. ");
        jsonObject.addProperty("playerMotionY", "Y velocity of the player being pushed by the explosion. ");
        jsonObject.addProperty("playerMotionZ", "Z velocity of the player being pushed by the explosion. ");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(ExplosionS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("x", packet.getX());
        jsonObject.addProperty("y", packet.getY());
        jsonObject.addProperty("z", packet.getZ());
        jsonObject.addProperty("strength", packet.getRadius());
        jsonObject.addProperty("recordCount", packet.getAffectedBlocks().size());
        jsonObject.add("records", ConvertUtils.GSON_INSTANCE.toJsonTree(packet.getAffectedBlocks()));
        jsonObject.addProperty("playerMotionX", packet.getPlayerVelocityX());
        jsonObject.addProperty("playerMotionY", packet.getPlayerVelocityY());
        jsonObject.addProperty("playerMotionZ", packet.getPlayerVelocityZ());
        return jsonObject;
    }
}
