package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.OpenWrittenBookS2CPacket;

public class OpenWrittenBookS2CPacketHandler implements BasePacketHandler<OpenWrittenBookS2CPacket> {
    @Override
    public String name() {
        return "OpenBook";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Open_Book";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sent when a player right clicks with a signed book. This tells the client to open the book GUI. ");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("hand", "The hand the player used to open the book. ");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(OpenWrittenBookS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("hand", packet.getHand().name());
        return jsonObject;
    }
}
