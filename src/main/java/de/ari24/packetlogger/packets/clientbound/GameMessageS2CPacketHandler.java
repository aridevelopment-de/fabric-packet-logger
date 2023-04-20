package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;

public class GameMessageS2CPacketHandler implements BasePacketHandler<GameMessageS2CPacket> {

    @Override
    public JsonObject serialize(GameMessageS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("content", packet.content().toString());
        jsonObject.addProperty("overlay", packet.overlay());
        return jsonObject;
    }
}
