package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;

public class GameMessageS2CPacketHandler implements BasePacketHandler<GameMessageS2CPacket> {
    @Override
    public String name() {
        return "SystemChatMessage";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#System_Chat_Message";
    }

    @Override
    public JsonObject serialize(GameMessageS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("message", packet.content().toString());
        jsonObject.addProperty("overlay", packet.overlay());
        return jsonObject;
    }
}
