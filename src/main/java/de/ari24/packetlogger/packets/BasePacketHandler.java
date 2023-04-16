package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.Packet;

public interface BasePacketHandler<T extends Packet<?>> {
    String name();
    String url();
    default JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "No description available.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        return jsonObject;
    }
    JsonObject serialize(T packet);
}
