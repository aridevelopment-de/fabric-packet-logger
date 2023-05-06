package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.UpdateDifficultyLockC2SPacket;

public class UpdateDifficultyLockC2SPacketHandler implements BasePacketHandler<UpdateDifficultyLockC2SPacket> {
    @Override
    public JsonObject serialize(UpdateDifficultyLockC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("difficultyLocked", packet.isDifficultyLocked());
        return jsonObject;
    }
}
