package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.UpdateDifficultyC2SPacket;

public class UpdateDifficultyC2SPacketHandler implements BasePacketHandler<UpdateDifficultyC2SPacket> {
    @Override
    public JsonObject serialize(UpdateDifficultyC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("difficulty", packet.getDifficulty().ordinal());
        return jsonObject;
    }
}
