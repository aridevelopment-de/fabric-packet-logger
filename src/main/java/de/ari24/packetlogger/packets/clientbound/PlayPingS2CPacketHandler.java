package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.PlayPingS2CPacket;

public class PlayPingS2CPacketHandler implements BasePacketHandler<PlayPingS2CPacket> {


    @Override
    public JsonObject serialize(PlayPingS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", packet.getParameter());
        return jsonObject;
    }
}
