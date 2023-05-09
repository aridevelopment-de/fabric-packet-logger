package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;

public class WorldTimeUpdateS2CPacketHandler implements BasePacketHandler<WorldTimeUpdateS2CPacket> {


    @Override
    public JsonObject serialize(WorldTimeUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("worldAge", packet.getTime());
        jsonObject.addProperty("timeOfDay", packet.getTimeOfDay());
        return jsonObject;
    }
}
