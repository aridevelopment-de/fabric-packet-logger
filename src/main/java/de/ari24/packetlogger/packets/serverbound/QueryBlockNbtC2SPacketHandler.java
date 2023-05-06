package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.QueryBlockNbtC2SPacket;

public class QueryBlockNbtC2SPacketHandler implements BasePacketHandler<QueryBlockNbtC2SPacket> {
    @Override
    public JsonObject serialize(QueryBlockNbtC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("transactionId", packet.getTransactionId());
        jsonObject.addProperty("location", packet.getPos().toShortString());
        return jsonObject;
    }
}
