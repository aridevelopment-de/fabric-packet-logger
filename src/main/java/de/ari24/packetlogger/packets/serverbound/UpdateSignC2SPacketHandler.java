package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.UpdateSignC2SPacket;

public class UpdateSignC2SPacketHandler implements BasePacketHandler<UpdateSignC2SPacket> {
    @Override
    public JsonObject serialize(UpdateSignC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("location", packet.getPos().toString());

        JsonArray array = new JsonArray();

        for (String line : packet.getText()) {
            array.add(line);
        }

        jsonObject.add("lines", array);
        return jsonObject;
    }
}
