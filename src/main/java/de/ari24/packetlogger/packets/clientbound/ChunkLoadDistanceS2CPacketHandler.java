package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.ChunkLoadDistanceS2CPacket;

public class ChunkLoadDistanceS2CPacketHandler implements BasePacketHandler<ChunkLoadDistanceS2CPacket> {
    @Override
    public String name() {
        return "SetRenderDistance";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Set_Render_Distance";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sent by the integrated singleplayer server when changing render distance. This packet is sent by the server when the client reappears in the overworld after leaving the end. ");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("distance", "Render distance (2-32).");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(ChunkLoadDistanceS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("distance", packet.getDistance());
        return jsonObject;
    }
}
