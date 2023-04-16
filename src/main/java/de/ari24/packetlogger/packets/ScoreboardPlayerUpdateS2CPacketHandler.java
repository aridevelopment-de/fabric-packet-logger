package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.ScoreboardPlayerUpdateS2CPacket;

public class ScoreboardPlayerUpdateS2CPacketHandler implements BasePacketHandler<ScoreboardPlayerUpdateS2CPacket> {
    @Override
    public String name() {
        return "UpdateScore";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Update_Score";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This is sent to the client when it should update a scoreboard item.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("entityName", "The entity whose score this is. For players, this is their username; for other entities, it is their UUID.");
        jsonObject.addProperty("action", "");
        jsonObject.addProperty("hasObjectiveName", "");
        jsonObject.addProperty("objectiveName", "The name opf the objective the score belongs to.");
        jsonObject.addProperty("value", "The score to be displayed next to the entry. Only sent when Action does not equal 1 (REMOVE). Here: Otherwise 0");
        return jsonObject;
    }

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
