package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.WorldBorderInterpolateSizeS2CPacket;

public class WorldBorderInterpolateSizeS2CPacketHandler implements BasePacketHandler<WorldBorderInterpolateSizeS2CPacket> {


    @Override
    public JsonObject serialize(WorldBorderInterpolateSizeS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("oldDiameter", packet.getSize());
        jsonObject.addProperty("newDiameter", packet.getSizeLerpTarget());
        jsonObject.addProperty("speed", packet.getSizeLerpTime());
        return jsonObject;
    }
}
