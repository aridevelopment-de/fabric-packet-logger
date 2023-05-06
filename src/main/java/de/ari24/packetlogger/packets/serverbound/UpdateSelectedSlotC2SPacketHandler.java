package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;

public class UpdateSelectedSlotC2SPacketHandler implements BasePacketHandler<UpdateSelectedSlotC2SPacket> {
    @Override
    public JsonObject serialize(UpdateSelectedSlotC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("selectedSlot", packet.getSelectedSlot());
        return jsonObject;
    }
}
