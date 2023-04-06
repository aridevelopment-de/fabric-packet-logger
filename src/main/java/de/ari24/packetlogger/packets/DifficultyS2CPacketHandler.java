package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.DifficultyS2CPacket;

public class DifficultyS2CPacketHandler implements BasePacketHandler<DifficultyS2CPacket> {
    @Override
    public String name() {
        return "ChangeDifficulty";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Change_Difficulty";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Changes the difficulty setting in the client's option menu ");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("difficulty", "The difficulty setting");
        jsonObject.addProperty("difficultyLocked", "Whether the difficulty is locked");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(DifficultyS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("difficulty", packet.getDifficulty().asString());
        jsonObject.addProperty("difficultyLocked", packet.isDifficultyLocked());
        return jsonObject;
    }
}
