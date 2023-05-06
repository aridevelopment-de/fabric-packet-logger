package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.handshake.HandshakeC2SPacket;

public class HandshakeC2SPacketHandler implements BasePacketHandler<HandshakeC2SPacket> {
    @Override
    public JsonObject serialize(HandshakeC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("protocolVersion", packet.getProtocolVersion());
        jsonObject.addProperty("address", packet.getAddress());
        jsonObject.addProperty("port", packet.getPort());
        jsonObject.addProperty("nextState", packet.getIntendedState().getId());
        return jsonObject;
    }
}
