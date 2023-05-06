package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.ClientStatusC2SPacket;

public class ClientStatusC2SPacketHandler implements BasePacketHandler<ClientStatusC2SPacket> {
    @Override
    public JsonObject serialize(ClientStatusC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("actionName", packet.getMode().name());
        jsonObject.addProperty("actionId", packet.getMode().ordinal());
        return jsonObject;
    }
}
