package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.ScoreboardObjectiveUpdateS2CPacket;

public class ScoreboardObjectiveUpdateS2CPacketHandler implements BasePacketHandler<ScoreboardObjectiveUpdateS2CPacket> {

    @Override
    public JsonObject serialize(ScoreboardObjectiveUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("objectiveName", packet.getName());

        String mode = null;

        switch (packet.getMode()) {
            case 0 -> mode = "Create Scoreboard";
            case 1 -> mode = "Remove Scoreboard";
            case 2 -> mode = "Update Display Text";
        }

        if (mode != null) {
            jsonObject.addProperty("mode", mode);
        } else {
            jsonObject.addProperty("mode", packet.getMode());
        }

        jsonObject.addProperty("objectiveValue", packet.getDisplayName().toString());
        jsonObject.addProperty("type", packet.getType().name());
        return jsonObject;
    }
}
