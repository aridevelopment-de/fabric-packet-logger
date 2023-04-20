package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.HealthUpdateS2CPacket;

public class HealthUpdateS2CPacketHandler implements BasePacketHandler<HealthUpdateS2CPacket> {


    @Override
    public JsonObject serialize(HealthUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("health", packet.getHealth());
        jsonObject.addProperty("food", packet.getFood());
        jsonObject.addProperty("saturation", packet.getSaturation());
        return jsonObject;
    }
}
