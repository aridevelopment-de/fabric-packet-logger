package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;

public class ClickSlotC2SPacketHandler implements BasePacketHandler<ClickSlotC2SPacket> {
    @Override
    public JsonObject serialize(ClickSlotC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("windowId", packet.getSyncId());
        jsonObject.addProperty("stateId", packet.getRevision());
        jsonObject.addProperty("slot", packet.getSlot());
        jsonObject.addProperty("button", packet.getButton());
        jsonObject.addProperty("mode", packet.getActionType().ordinal());

        JsonArray array = new JsonArray();

        for (Int2ObjectMap.Entry<ItemStack> entry : packet.getModifiedStacks().int2ObjectEntrySet()) {
            JsonObject jsonObject1 = new JsonObject();
            jsonObject1.addProperty("slot", entry.getIntKey());
            jsonObject1.add("item", ConvertUtils.serializeItemStack(entry.getValue()));
            array.add(jsonObject1);
        }

        jsonObject.addProperty("slots", array.toString());
        jsonObject.add("carriedItem", ConvertUtils.serializeItemStack(packet.getStack()));
        return jsonObject;
    }
}
