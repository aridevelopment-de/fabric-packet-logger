package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.DifficultyS2CPacket;
import net.minecraft.world.Difficulty;

public class DifficultyS2CPacketHandler implements BasePacketHandler<DifficultyS2CPacket> {

    @Override
    public DifficultyS2CPacket deserialize(Class<DifficultyS2CPacket> clazz, JsonObject json) throws Exception {
        Difficulty difficulty = Difficulty.byId(json.get("difficultyId").getAsInt());
        boolean difficultyLocked = json.get("difficultyLocked").getAsBoolean();
        return new DifficultyS2CPacket(difficulty, difficultyLocked);
    }

    @Override
    public JsonObject serialize(DifficultyS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("difficulty", packet.getDifficulty().asString());
        jsonObject.addProperty("difficultyId", packet.getDifficulty().getId());
        jsonObject.addProperty("difficultyLocked", packet.isDifficultyLocked());
        return jsonObject;
    }
}
