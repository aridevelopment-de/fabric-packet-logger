package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.WorldBorderSizeChangedS2CPacket;

public class WorldBorderSizeChangedS2CPacketHandler implements BasePacketHandler<WorldBorderSizeChangedS2CPacket> {


    @Override
    public JsonObject serialize(WorldBorderSizeChangedS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("diameter", packet.getSizeLerpTarget());
        return jsonObject;
    }
}
