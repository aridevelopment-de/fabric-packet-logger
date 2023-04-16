package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
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
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Container_Content";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sent by the server when items in multiple slots (in a window) are added/removed. This includes the main inventory, equipped armour and crafting slots. This packet with Window ID set to \"0\" is sent during the player joining sequence to initialise the player's inventory. ");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("windowId", "The ID of window which items are being sent for. 0 for player inventory. ");
        jsonObject.addProperty("stateId", "The last received State ID from either a Set Container Slot or a Set Container Content packet ");
        jsonObject.addProperty("slotData", "An array of slots (item id, count, nbt). See https://wiki.vg/Slot_Data");
        jsonObject.addProperty("carriedItem", "The item the player is currently holding. See https://wiki.vg/Slot_Data");
        return jsonObject;
    }

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
