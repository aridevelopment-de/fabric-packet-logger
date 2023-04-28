package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.PlaySoundFromEntityS2CPacket;

public class PlaySoundFromEntityS2CPacketHandler implements BasePacketHandler<PlaySoundFromEntityS2CPacket> {
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
