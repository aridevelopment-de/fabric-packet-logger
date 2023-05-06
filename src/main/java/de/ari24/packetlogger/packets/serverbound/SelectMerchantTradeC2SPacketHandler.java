package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.SelectMerchantTradeC2SPacket;

public class SelectMerchantTradeC2SPacketHandler implements BasePacketHandler<SelectMerchantTradeC2SPacket> {
    @Override
    public JsonObject serialize(SelectMerchantTradeC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("tradeId", packet.getTradeId());
        return jsonObject;
    }
}
