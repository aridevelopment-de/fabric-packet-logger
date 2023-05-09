package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.CloseScreenS2CPacket;

public class CloseScreenS2CPacketHandler implements BasePacketHandler<CloseScreenS2CPacket> {
    @Override
    public JsonObject serialize(CloseScreenS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("windowId", packet.getSyncId());
        return jsonObject;
    }
}
