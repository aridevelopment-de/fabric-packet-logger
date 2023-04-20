package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.PlayerActionResponseS2CPacket;

public class PlayerActionResponseS2CPacketHandler implements BasePacketHandler<PlayerActionResponseS2CPacket> {

    @Override
    public JsonObject serialize(PlayerActionResponseS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("sequenceId", packet.sequence());
        return jsonObject;
    }
}
