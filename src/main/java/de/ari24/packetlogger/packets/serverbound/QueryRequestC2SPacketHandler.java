package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.query.QueryRequestC2SPacket;

public class QueryRequestC2SPacketHandler implements BasePacketHandler<QueryRequestC2SPacket> {
    @Override
    public JsonObject serialize(QueryRequestC2SPacket packet) {
        return new JsonObject();
    }
}
