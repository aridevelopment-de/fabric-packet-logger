package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;

public class PlaySoundS2CPacketHandler implements BasePacketHandler<PlaySoundS2CPacket> {
    @Override
    public String id() {
        return "SoundEffect";
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
