package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.InventoryS2CPacket;

import java.util.ArrayList;
import java.util.List;

public class InventoryS2CPacketHandler implements BasePacketHandler<InventoryS2CPacket> {
    @Override
    public String name() {
        return "SetContainerContent";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Set_Container_Content";
    }

    @Override
    public JsonObject serialize(InventoryS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("syncId", packet.getSyncId());
        jsonObject.addProperty("revision", packet.getRevision());

        List<JsonObject> itemStacks = new ArrayList<>();
        packet.getContents().forEach((itemStack) -> itemStacks.add(ConvertUtils.serializeItemStack(itemStack)));
        jsonObject.add("itemStacks", ConvertUtils.GSON_INSTANCE.toJsonTree(itemStacks));
        jsonObject.add("cursorStack", ConvertUtils.serializeItemStack(packet.getCursorStack()));
        return jsonObject;
    }
}
