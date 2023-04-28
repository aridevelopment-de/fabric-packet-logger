package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.PlayerSpawnPositionS2CPacket;

public class PlayerSpawnPositionS2CPacketHandler implements BasePacketHandler<PlayerSpawnPositionS2CPacket> {

    @Override
    public JsonObject serialize(PlayerSpawnPositionS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("location", packet.getPos().toString());
        jsonObject.addProperty("angle", packet.getAngle());
        return jsonObject;
    }
}
