package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.SubtitleS2CPacket;

public class SubtitleS2CPacketHandler implements BasePacketHandler<SubtitleS2CPacket> {

    @Override
    public JsonObject serialize(SubtitleS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("subtitleText", packet.getSubtitle().toString());
        return jsonObject;
    }
}
