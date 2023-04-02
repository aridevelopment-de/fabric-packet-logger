package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.PlayerSpawnPositionS2CPacket;

public class PlayerSpawnPositionS2CPacketHandler implements BasePacketHandler<PlayerSpawnPositionS2CPacket> {
    @Override
    public String id() {
        return "SetDefaultSpawnPosition";
    }

    @Override
    public JsonObject serialize(PlayerSpawnPositionS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("pos", packet.getPos().toShortString());
        jsonObject.addProperty("angle", packet.getAngle());
        return jsonObject;
    }
}
