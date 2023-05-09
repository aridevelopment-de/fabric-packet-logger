package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.login.LoginQueryRequestS2CPacket;

public class LoginQueryRequestS2CPacketHandler implements BasePacketHandler<LoginQueryRequestS2CPacket> {

    @Override
    public JsonObject serialize(LoginQueryRequestS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("messageId", packet.getQueryId());
        jsonObject.addProperty("channel", packet.getChannel().toString());

        byte[] data = new byte[]{};
        packet.getPayload().readBytes(data);
        packet.getPayload().resetReaderIndex();

        jsonObject.add("data", ConvertUtils.GSON_INSTANCE.toJsonTree(data));
        return jsonObject;
    }
}
