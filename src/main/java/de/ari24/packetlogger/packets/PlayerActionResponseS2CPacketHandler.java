package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.PlayerActionResponseS2CPacket;

public class PlayerActionResponseS2CPacketHandler implements BasePacketHandler<PlayerActionResponseS2CPacket> {
    @Override
    public String name() {
        return "AcknowledgeBlockChange";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Acknowledge_Block_Change";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Acknowledges a user-initiated block change. After receiving this packet, the client will display the block state sent by the server instead of the one predicted by the client. ");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("sequenceId", "Represents the sequence to acknowledge, this is used for properly syncing block changes to the client after interactions.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(PlayerActionResponseS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("sequenceId", packet.sequence());
        return jsonObject;
    }
}
