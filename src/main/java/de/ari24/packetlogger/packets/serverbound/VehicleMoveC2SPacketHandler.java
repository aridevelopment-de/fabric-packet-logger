package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.VehicleMoveC2SPacket;

public class VehicleMoveC2SPacketHandler implements BasePacketHandler<VehicleMoveC2SPacket> {
    @Override
    public JsonObject serialize(VehicleMoveC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("x", packet.getX());
        jsonObject.addProperty("y", packet.getY());
        jsonObject.addProperty("z", packet.getZ());
        jsonObject.addProperty("yaw", packet.getYaw());
        jsonObject.addProperty("pitch", packet.getPitch());
        return jsonObject;
    }
}
