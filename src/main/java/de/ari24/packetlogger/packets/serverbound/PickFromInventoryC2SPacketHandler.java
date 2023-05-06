package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.PickFromInventoryC2SPacket;

public class PickFromInventoryC2SPacketHandler implements BasePacketHandler<PickFromInventoryC2SPacket> {
    @Override
    public JsonObject serialize(PickFromInventoryC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("slot", packet.getSlot());
        return jsonObject;
    }
}
