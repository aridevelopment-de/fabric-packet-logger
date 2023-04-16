package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.StopSoundS2CPacket;

public class StopSoundS2CPacketHandler implements BasePacketHandler<StopSoundS2CPacket> {
    @Override
    public String name() {
        return "StopSound";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Stop_Sound";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Stops an active sound.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("soundId", "The sound to stop. If null, all sounds will be stopped.");
        jsonObject.addProperty("category", "The category of sounds to stop. If null, all sounds will be stopped.");
        return jsonObject;
    }

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
