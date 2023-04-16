package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.WorldBorderSizeChangedS2CPacket;

public class WorldBorderSizeChangedS2CPacketHandler implements BasePacketHandler<WorldBorderSizeChangedS2CPacket> {
    @Override
    public String name() {
        return "SetBorderSize";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Border_Size";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sets the size of the world border.");
        jsonObject.addProperty("diameter", "Length of a single side of the world border, in meters.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(WorldBorderSizeChangedS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("diameter", packet.getSizeLerpTarget());
        return jsonObject;
    }
}
