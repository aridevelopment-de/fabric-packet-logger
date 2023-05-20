package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.ChunkLoadDistanceS2CPacket;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChunkLoadDistanceS2CPacketHandler implements BasePacketHandler<ChunkLoadDistanceS2CPacket> {
    @Override
    public @Nullable List<String> getJsonParameterOrder() {
        return List.of("distance");
    }

    @Override
    public JsonObject serialize(ChunkLoadDistanceS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("distance", packet.getDistance());
        return jsonObject;
    }
}
