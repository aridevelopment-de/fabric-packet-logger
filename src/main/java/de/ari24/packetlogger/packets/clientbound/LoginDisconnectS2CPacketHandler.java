package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.login.LoginDisconnectS2CPacket;

public class LoginDisconnectS2CPacketHandler implements BasePacketHandler<LoginDisconnectS2CPacket> {

    @Override
    public JsonObject serialize(LoginDisconnectS2CPacket packet) {
        JsonObject jsonObjects = new JsonObject();
        jsonObjects.addProperty("reason", packet.getReason().getString());
        return jsonObjects;
    }
}
