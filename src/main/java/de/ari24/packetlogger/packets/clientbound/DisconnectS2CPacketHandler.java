package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.DisconnectS2CPacket;

public class DisconnectS2CPacketHandler implements BasePacketHandler<DisconnectS2CPacket> {


    @Override
    public JsonObject serialize(DisconnectS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("reason", packet.getReason().toString());
        return jsonObject;
    }
}
