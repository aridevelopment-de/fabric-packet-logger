package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.NbtQueryResponseS2CPacket;

public class NbtQueryResponseS2CPacketHandler implements BasePacketHandler<NbtQueryResponseS2CPacket> {
    @Override
    public String name() {
        return "TagQueryResponse";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Tag_Query_Response";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sent in response to Query Block Entity Tag or Query Entity Tag packet.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("transactionId", "Can be compared to the one sent in the original query packet. ");
        jsonObject.addProperty("nbt", "The NBT of the block or entity. May be a TAG_END (0) in which case no NBT is present. ");
        return jsonObject;
    }

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
