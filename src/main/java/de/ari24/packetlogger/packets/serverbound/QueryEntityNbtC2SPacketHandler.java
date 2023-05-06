package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.c2s.play.QueryEntityNbtC2SPacket;

public class QueryEntityNbtC2SPacketHandler implements BasePacketHandler<QueryEntityNbtC2SPacket> {
    @Override
    public JsonObject serialize(QueryEntityNbtC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("transactionId", packet.getTransactionId());
        ConvertUtils.appendEntity(jsonObject, packet.getEntityId());
        return jsonObject;
    }
}
