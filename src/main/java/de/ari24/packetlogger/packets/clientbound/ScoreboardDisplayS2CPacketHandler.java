package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.ScoreboardDisplayS2CPacket;

public class ScoreboardDisplayS2CPacketHandler implements BasePacketHandler<ScoreboardDisplayS2CPacket> {


    @Override
    public JsonObject serialize(ScoreboardDisplayS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();

        String position = null;

        switch (packet.getSlot()) {
            case 0 -> position = "Player List";
            case 1 -> position = "Sidebar";
            case 2 -> position = "Below Name";
        }

        if (packet.getSlot() >= 3 && packet.getSlot() <= 18) {
            position = "Sidebar for Team " + (packet.getSlot() - 3);
        }

        if (position != null) {
            jsonObject.addProperty("position", position);
        } else {
            jsonObject.addProperty("position", packet.getSlot());
        }

        jsonObject.addProperty("scoreName", packet.getName());
        return jsonObject;
    }
}
