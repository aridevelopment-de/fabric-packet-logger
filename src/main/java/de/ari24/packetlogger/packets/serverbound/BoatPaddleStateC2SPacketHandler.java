package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.BoatPaddleStateC2SPacket;

public class BoatPaddleStateC2SPacketHandler implements BasePacketHandler<BoatPaddleStateC2SPacket> {
    @Override
    public JsonObject serialize(BoatPaddleStateC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("leftPaddling", packet.isLeftPaddling());
        jsonObject.addProperty("rightPaddling", packet.isRightPaddling());
        return jsonObject;
    }
}
