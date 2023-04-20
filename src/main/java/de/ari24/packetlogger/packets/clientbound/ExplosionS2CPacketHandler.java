package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;

public class ExplosionS2CPacketHandler implements BasePacketHandler<ExplosionS2CPacket> {

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
