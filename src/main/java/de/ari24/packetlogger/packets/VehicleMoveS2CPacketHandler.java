package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.VehicleMoveS2CPacket;

public class VehicleMoveS2CPacketHandler implements BasePacketHandler<VehicleMoveS2CPacket> {
    @Override
    public String name() {
        return "MoveVehicle";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Move_Vehicle";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Informs the client that the current vehicle (entity that the player is riding) has moved.");
        jsonObject.addProperty("wikiVgNotes", "Note that all fields use absolute positioning and do not allow for relative positioning. ");
        jsonObject.addProperty("x", "");
        jsonObject.addProperty("y", "");
        jsonObject.addProperty("z", "");
        jsonObject.addProperty("yaw", "Absolute rotation on the vertical axis, in degrees");
        jsonObject.addProperty("pitch", "Absolute rotation on the horizontal axis, in degrees");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(VehicleMoveS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("x", packet.getX());
        jsonObject.addProperty("y", packet.getY());
        jsonObject.addProperty("z", packet.getZ());
        jsonObject.addProperty("yaw", packet.getYaw());
        jsonObject.addProperty("pitch", packet.getPitch());
        return jsonObject;
    }
}
