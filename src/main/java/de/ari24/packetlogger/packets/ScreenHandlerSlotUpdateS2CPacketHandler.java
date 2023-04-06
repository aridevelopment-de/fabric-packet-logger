package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;

public class ScreenHandlerSlotUpdateS2CPacketHandler implements BasePacketHandler<ScreenHandlerSlotUpdateS2CPacket> {
    @Override
    public String name() {
        return "SetContainerSlot";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Set_Container_Slot";
    }

    @Override
    public JsonObject serialize(ScreenHandlerSlotUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("syncId", packet.getSyncId());
        jsonObject.addProperty("revision", packet.getRevision());
        jsonObject.addProperty("slot", packet.getSlot());
        jsonObject.add("itemStack", ConvertUtils.serializeItemStack(packet.getItemStack()));
        return jsonObject;
    }
}
