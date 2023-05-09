package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.ScoreboardPlayerUpdateS2CPacket;

public class ScoreboardPlayerUpdateS2CPacketHandler implements BasePacketHandler<ScoreboardPlayerUpdateS2CPacket> {

    @Override
    public JsonObject serialize(ScoreboardPlayerUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("entityName", packet.getPlayerName());
        jsonObject.addProperty("action", packet.getUpdateMode().name());
        jsonObject.addProperty("hasObjectiveName", packet.getObjectiveName() != null);

        if (packet.getObjectiveName() != null) {
            jsonObject.addProperty("objectiveName", packet.getObjectiveName());
        }

        jsonObject.addProperty("value", packet.getScore());
        return jsonObject;
    }
}
