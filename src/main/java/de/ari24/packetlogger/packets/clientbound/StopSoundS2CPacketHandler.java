package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.StopSoundS2CPacket;

public class StopSoundS2CPacketHandler implements BasePacketHandler<StopSoundS2CPacket> {

    @Override
    public JsonObject serialize(StopSoundS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();

        if (packet.getSoundId() != null) {
            jsonObject.addProperty("soundId", packet.getSoundId().toString());
        } else {
            jsonObject.add("soundId", JsonNull.INSTANCE);
        }

        if (packet.getCategory() != null) {
            jsonObject.addProperty("category", packet.getCategory().toString());
        } else {
            jsonObject.add("category", JsonNull.INSTANCE);
        }

        return jsonObject;
    }
}
