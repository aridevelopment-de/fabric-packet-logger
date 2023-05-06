package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.BookUpdateC2SPacket;

public class BookUpdateC2SPacketHandler implements BasePacketHandler<BookUpdateC2SPacket> {
    @Override
    public JsonObject serialize(BookUpdateC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("slot", packet.getSlot());
        jsonObject.addProperty("pageCount", packet.getPages().size());

        JsonArray array = new JsonArray();
        packet.getPages().forEach(array::add);
        jsonObject.add("entries", array);

        if (packet.getTitle().isPresent()) {
            jsonObject.addProperty("title", packet.getTitle().get());
        }

        return jsonObject;
    }
}
