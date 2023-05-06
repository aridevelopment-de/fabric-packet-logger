package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.login.LoginQueryResponseC2SPacket;

public class LoginQueryResponseC2SPacketHandler implements BasePacketHandler<LoginQueryResponseC2SPacket> {
    @Override
    public JsonObject serialize(LoginQueryResponseC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("messageId", packet.getQueryId());

        byte[] bytes = new byte[]{};

        if (packet.getResponse() != null) {
            packet.getResponse().readBytes(bytes);
            packet.getResponse().resetReaderIndex();
        }

        jsonObject.addProperty("data", new String(bytes));
        return jsonObject;
    }
}
