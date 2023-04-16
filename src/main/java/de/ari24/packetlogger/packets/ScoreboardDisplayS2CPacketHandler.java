package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.ScoreboardDisplayS2CPacket;

public class ScoreboardDisplayS2CPacketHandler implements BasePacketHandler<ScoreboardDisplayS2CPacket> {
    @Override
    public String name() {
        return "DisplayObjective";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Display_Objective";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This is sent to the client when it should display a scoreboard");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("position", "The position of the scoreboard. String if resolvable, otherwise a byte.");
        jsonObject.addProperty("scoreName", "The unique name for the scoreboard to be displayed");
        return jsonObject;
    }

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
