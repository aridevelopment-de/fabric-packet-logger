package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.WorldBorderCenterChangedS2CPacket;

public class WorldBorderCenterChangedS2CPacketHandler implements BasePacketHandler<WorldBorderCenterChangedS2CPacket> {


    @Override
    public JsonObject serialize(WorldBorderCenterChangedS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("centerX", packet.getCenterX());
        jsonObject.addProperty("centerZ", packet.getCenterZ());
        return jsonObject;
    }
}
