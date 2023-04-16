package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.PlaySoundFromEntityS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class PlaySoundFromEntityS2CPacketHandler implements BasePacketHandler<PlaySoundFromEntityS2CPacket> {
    @Override
    public String name() {
        return "EntitySoundEffect";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Entity_Sound_Effect";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Plays a sound effect from an entity, either by hardcoded ID or Identifier. Sound IDS and names can be found here: https://pokechu22.github.io/Burger/1.19.4.html#sounds");
        jsonObject.addProperty("wikiVgNotes", "Numeric sound effect IDs are liable to change between versions");
        jsonObject.addProperty("soundId", "Represents the Sound ID + 1. If the value is 0, the packet contains a sound specified by Identifier.");
        jsonObject.addProperty("category", "The category that this sound will be played from. Current categories: https://gist.github.com/konwboj/7c0c380d3923443e9d55");
        jsonObject.addProperty("volume", "1.0 is 100%, capped between 0.0 and 1.0 by Notchian clients");
        jsonObject.addProperty("pitch", "Float between 0.5 and 2.0 by Notchian clients. ");
        jsonObject.addProperty("seed", "Seed used to pick sound variant. ");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(PlaySoundFromEntityS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("soundId", packet.getSound().value().getId().toString());
        jsonObject.addProperty("soundCategory", packet.getCategory().getName());
        ConvertUtils.appendEntity(jsonObject, packet.getEntityId());
        jsonObject.addProperty("volume", packet.getVolume());
        jsonObject.addProperty("pitch", packet.getPitch());
        jsonObject.addProperty("seed", packet.getSeed());
        return jsonObject;
    }
}
