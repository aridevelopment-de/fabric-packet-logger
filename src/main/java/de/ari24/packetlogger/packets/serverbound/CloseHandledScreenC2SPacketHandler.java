package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;

public class CloseHandledScreenC2SPacketHandler implements BasePacketHandler<CloseHandledScreenC2SPacket> {
    @Override
    public JsonObject serialize(CloseHandledScreenC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("windowId", packet.getSyncId());
        return jsonObject;
    }
}
