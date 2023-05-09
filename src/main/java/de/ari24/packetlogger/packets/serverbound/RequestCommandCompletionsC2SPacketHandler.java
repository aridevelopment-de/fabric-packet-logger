package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.RequestCommandCompletionsC2SPacket;

public class RequestCommandCompletionsC2SPacketHandler implements BasePacketHandler<RequestCommandCompletionsC2SPacket> {
    @Override
    public JsonObject serialize(RequestCommandCompletionsC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("transactionId", packet.getCompletionId());
        jsonObject.addProperty("partialCommand", packet.getPartialCommand());
        return jsonObject;
    }
}
