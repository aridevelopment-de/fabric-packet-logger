package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.SimulationDistanceS2CPacket;

public class SimulationDistanceS2CPacketHandler implements BasePacketHandler<SimulationDistanceS2CPacket> {


    @Override
    public JsonObject serialize(SimulationDistanceS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("simulationDistance", packet.simulationDistance());
        return jsonObject;
    }
}
