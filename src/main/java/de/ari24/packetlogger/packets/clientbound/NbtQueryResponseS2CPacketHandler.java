package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.NbtQueryResponseS2CPacket;

public class NbtQueryResponseS2CPacketHandler implements BasePacketHandler<NbtQueryResponseS2CPacket> {

    @Override
    public JsonObject serialize(NbtQueryResponseS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("transactionId", packet.getTransactionId());

        if (packet.getNbt() != null) {
            jsonObject.addProperty("nbt", packet.getNbt().toString());
        }

        return jsonObject;
    }
}
