package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.JigsawGeneratingC2SPacket;

public class JigsawGeneratingC2SPacketHandler implements BasePacketHandler<JigsawGeneratingC2SPacket> {
    @Override
    public JsonObject serialize(JigsawGeneratingC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("location", packet.getPos().toString());
        jsonObject.addProperty("maxDepth", packet.getMaxDepth());
        jsonObject.addProperty("keepJigsaws", packet.shouldKeepJigsaws());
        return jsonObject;
    }
}
