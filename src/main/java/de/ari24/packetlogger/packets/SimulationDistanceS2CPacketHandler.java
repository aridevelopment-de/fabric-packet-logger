package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.SimulationDistanceS2CPacket;

public class SimulationDistanceS2CPacketHandler implements BasePacketHandler<SimulationDistanceS2CPacket> {
    @Override
    public String name() {
        return "SetSimulationDistance";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Set_Simulation_Distance";
    }

    @Override
    public JsonObject serialize(SimulationDistanceS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("simulationDistance", packet.simulationDistance());
        return jsonObject;
    }
}
