package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.KeepAliveS2CPacket;

public class KeepAliveS2CPacketHandler implements BasePacketHandler<KeepAliveS2CPacket> {


    @Override
    public JsonObject serialize(KeepAliveS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("keepAliveId", packet.getId());
        return jsonObject;
    }
}
