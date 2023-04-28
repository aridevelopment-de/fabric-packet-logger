package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.InventoryS2CPacket;

import java.util.ArrayList;
import java.util.List;

public class InventoryS2CPacketHandler implements BasePacketHandler<InventoryS2CPacket> {

    @Override
    public JsonObject serialize(InventoryS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("windowId", packet.getSyncId());
        jsonObject.addProperty("stateId", packet.getRevision());

        List<JsonObject> itemStacks = new ArrayList<>();
        packet.getContents().forEach((itemStack) -> itemStacks.add(ConvertUtils.serializeItemStack(itemStack)));
        jsonObject.add("slotData", ConvertUtils.GSON_INSTANCE.toJsonTree(itemStacks));
        jsonObject.add("carriedItem", ConvertUtils.serializeItemStack(packet.getCursorStack()));
        return jsonObject;
    }
}
