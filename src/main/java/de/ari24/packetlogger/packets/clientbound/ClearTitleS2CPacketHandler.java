package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.ClearTitleS2CPacket;

public class ClearTitleS2CPacketHandler implements BasePacketHandler<ClearTitleS2CPacket> {
    @Override
    public JsonObject serialize(ClearTitleS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("reset", packet.shouldReset());
        return jsonObject;
    }
}
