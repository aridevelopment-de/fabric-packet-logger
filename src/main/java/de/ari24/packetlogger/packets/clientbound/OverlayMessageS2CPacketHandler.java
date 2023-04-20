package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;

public class OverlayMessageS2CPacketHandler implements BasePacketHandler<OverlayMessageS2CPacket> {

    @Override
    public JsonObject serialize(OverlayMessageS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("message", packet.getMessage().toString());
        return jsonObject;
    }
}
