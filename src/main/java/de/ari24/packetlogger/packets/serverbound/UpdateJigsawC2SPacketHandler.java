package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.UpdateJigsawC2SPacket;

public class UpdateJigsawC2SPacketHandler implements BasePacketHandler<UpdateJigsawC2SPacket> {
    @Override
    public JsonObject serialize(UpdateJigsawC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("pos", packet.getPos().toShortString());
        jsonObject.addProperty("name", packet.getName().toString());
        jsonObject.addProperty("target", packet.getTarget().toString());
        jsonObject.addProperty("pool", packet.getPool().toString());
        jsonObject.addProperty("finalState", packet.getFinalState());
        jsonObject.addProperty("joint", packet.getJointType().asString());
        return jsonObject;
    }
}
