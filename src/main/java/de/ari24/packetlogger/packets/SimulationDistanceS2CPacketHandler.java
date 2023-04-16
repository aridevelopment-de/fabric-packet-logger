package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.SimulationDistanceS2CPacket;

public class SimulationDistanceS2CPacketHandler implements BasePacketHandler<SimulationDistanceS2CPacket> {
    @Override
    public String name() {
        return "SetSimulationDistance";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Simulation_Distance";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Updates the simulation distance of the client.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("simulationDistance", "The distance that the client will process specific things, such as entities.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(SimulationDistanceS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("simulationDistance", packet.simulationDistance());
        return jsonObject;
    }
}
