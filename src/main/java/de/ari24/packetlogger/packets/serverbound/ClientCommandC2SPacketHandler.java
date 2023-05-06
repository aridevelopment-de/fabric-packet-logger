package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;

public class ClientCommandC2SPacketHandler implements BasePacketHandler<ClientCommandC2SPacket> {
    @Override
    public JsonObject serialize(ClientCommandC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.getEntityId());
        jsonObject.addProperty("actionId", packet.getMode().ordinal());
        jsonObject.addProperty("jumpBoost", packet.getMountJumpHeight());
        return jsonObject;
    }
}
