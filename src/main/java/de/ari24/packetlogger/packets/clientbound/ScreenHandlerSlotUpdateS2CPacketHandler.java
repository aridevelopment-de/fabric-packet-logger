package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;

public class ScreenHandlerSlotUpdateS2CPacketHandler implements BasePacketHandler<ScreenHandlerSlotUpdateS2CPacket> {


    @Override
    public JsonObject serialize(ScreenHandlerSlotUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("windowId", packet.getSyncId());
        jsonObject.addProperty("stateId", packet.getRevision());
        jsonObject.addProperty("slot", packet.getSlot());
        jsonObject.add("slotData", ConvertUtils.serializeItemStack(packet.getItemStack()));
        return jsonObject;
    }
}
