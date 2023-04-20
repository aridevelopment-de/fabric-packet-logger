package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.UpdateSelectedSlotS2CPacket;

public class UpdateSelectedSlotS2CPacketHandler implements BasePacketHandler<UpdateSelectedSlotS2CPacket> {


    @Override
    public JsonObject serialize(UpdateSelectedSlotS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("slot", packet.getSlot());
        return jsonObject;
    }
}

