package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.ScoreboardObjectiveUpdateS2CPacket;

public class ScoreboardObjectiveUpdateS2CPacketHandler implements BasePacketHandler<ScoreboardObjectiveUpdateS2CPacket> {
    @Override
    public String name() {
        return "UpdateObjectives";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Update_Objectives";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This is sent to the client when it should create a new scoreboard objective or remove one.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("objectiveName", "A unique name for the objective.");
        jsonObject.addProperty("mode", "String if the mode is known, otherwise the integer itself");
        jsonObject.addProperty("objectiveValue", "Only if the mode is 0 (Create Scoreboard) or 2 (Update Display Text). The text to be displayed for the score.");
        jsonObject.addProperty("type", "Only if mode is 0 (Create Scoreboard) or 2 (Update Display Text)"); // TODO: What does hearts even mean?
        return jsonObject;
    }

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
