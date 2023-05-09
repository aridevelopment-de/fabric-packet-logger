package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.DifficultyS2CPacket;

public class DifficultyS2CPacketHandler implements BasePacketHandler<DifficultyS2CPacket> {

    @Override
    public JsonObject serialize(DifficultyS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("difficulty", packet.getDifficulty().asString());
        jsonObject.addProperty("difficultyLocked", packet.isDifficultyLocked());
        return jsonObject;
    }
}
