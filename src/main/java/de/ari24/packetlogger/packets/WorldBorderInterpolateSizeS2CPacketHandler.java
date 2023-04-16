package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.WorldBorderInterpolateSizeS2CPacket;

public class WorldBorderInterpolateSizeS2CPacketHandler implements BasePacketHandler<WorldBorderInterpolateSizeS2CPacket> {
    @Override
    public String name() {
        return "SetBorderLerpSize";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Border_Lerp_Size";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sets the world border to interpolate between the current diameter and the new diameter over the given number of real-time milliseconds.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("oldDiameter", "Current length of a single side of the world border, in meters. ");
        jsonObject.addProperty("newDiameter", "Target length of a single side of the world border, in meters.");
        jsonObject.addProperty("speed", "Number of real-time milliseconds until New Diameter is reached. It appears that Notchian server does not sync world border speed to game ticks, so it gets out of sync with server lag. If the world border is not moving, this is set to 0");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(WorldBorderInterpolateSizeS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("oldDiameter", packet.getSize());
        jsonObject.addProperty("newDiameter", packet.getSizeLerpTarget());
        jsonObject.addProperty("speed", packet.getSizeLerpTime());
        return jsonObject;
    }
}
