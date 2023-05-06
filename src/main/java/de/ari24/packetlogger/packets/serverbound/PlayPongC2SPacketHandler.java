package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.PlayPongC2SPacket;

public class PlayPongC2SPacketHandler implements BasePacketHandler<PlayPongC2SPacket> {
    @Override
    public JsonObject serialize(PlayPongC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", packet.getParameter());
        return jsonObject;
    }
}
