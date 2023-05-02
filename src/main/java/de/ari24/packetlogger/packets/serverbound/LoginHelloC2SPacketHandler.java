package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.login.LoginHelloC2SPacket;

public class LoginHelloC2SPacketHandler implements BasePacketHandler<LoginHelloC2SPacket> {
    @Override
    public JsonObject serialize(LoginHelloC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", packet.name());

        if (packet.profileId().isPresent()) {
            jsonObject.addProperty("uuid", packet.profileId().get().toString());
        }

        return jsonObject;
    }
}
