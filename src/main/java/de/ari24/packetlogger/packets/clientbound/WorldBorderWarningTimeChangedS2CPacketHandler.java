package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.WorldBorderWarningTimeChangedS2CPacket;

public class WorldBorderWarningTimeChangedS2CPacketHandler implements BasePacketHandler<WorldBorderWarningTimeChangedS2CPacket> {
    @Override
    public String name() {
        return "SetBorderWarningDelay";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Set_Border_Warning_Delay";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet is sent to the client to set the warning time of the world border when being within the warning radius. A red vignette will be displayed if the player is within that distance.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("warningTime", "The warning time in ticks until the red vignette gets displayed.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(WorldBorderWarningTimeChangedS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("warningTime", packet.getWarningTime());
        return jsonObject;
    }
}
