package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;

public class PlaySoundS2CPacketHandler implements BasePacketHandler<PlaySoundS2CPacket> {
    @Override
    public String name() {
        return "SoundEffect";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Sound_Effect";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Plays a sound effect at the given location, either by hardcoded ID or Identifier. Sound IDs and names can be found here: https://pokechu22.github.io/Burger/1.19.4.html#sounds");
        jsonObject.addProperty("wikiVgNotes", "Numeric sound effect IDs are liable to change between versions");
        jsonObject.addProperty("sound", "Represents the Sound ID + 1. If the value is 0, the packet contains a sound specified by Identifier.");
        jsonObject.addProperty("category", "The category that this sound will be played from. Current categories: https://gist.github.com/konwboj/7c0c380d3923443e9d55");
        jsonObject.addProperty("fixedX", "Effect X multiplied by 8 (fixed-point number with only 3 bits dedicated to the fractional part). ");
        jsonObject.addProperty("fixedY", "Effect Y multiplied by 8 (fixed-point number with only 3 bits dedicated to the fractional part).");
        jsonObject.addProperty("fixedZ", "Effect Z multiplied by 8 (fixed-point number with only 3 bits dedicated to the fractional part).");
        jsonObject.addProperty("volume", "1.0 is 100%, capped between 0.0 and 1.0 by Notchian clients");
        jsonObject.addProperty("pitch", "Float between 0.5 and 2.0 by Notchian clients. ");
        jsonObject.addProperty("seed", "Seed used to pick sound variant. ");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(PlaySoundS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("sound", packet.getSound().toString());
        jsonObject.addProperty("category", packet.getCategory().toString());
        jsonObject.addProperty("fixedX", packet.getX());
        jsonObject.addProperty("fixedY", packet.getY());
        jsonObject.addProperty("fixedZ", packet.getZ());
        jsonObject.addProperty("volume", packet.getVolume());
        jsonObject.addProperty("pitch", packet.getPitch());
        jsonObject.addProperty("seed", packet.getSeed());
        return jsonObject;
    }
}
