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
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Container_Slot";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sent by the server when an item in a slot (in a window) is added/removed. ");
        jsonObject.addProperty("wikiVgNotes", """
                To set the cursor (the item currently dragged with the mouse), use -1 as Window ID and as Slot.     
                This packet can only be used to edit the hotbar and offhand of the player's inventory if window ID is set to 0 (slots 36 through 45) if the player is in creative, with their inventory open, and not in their survival inventory tab. Otherwise, when window ID is 0, it can edit any slot in the player's inventory. If the window ID is set to -2, then any slot in the inventory can be used but no add item animation will be played.\s
                """);
        jsonObject.addProperty("windowId", "The window which is being updated. 0 for player inventory. Note that all known window types include the player inventory. This packet will only be sent for the currently opened window while the player is performing actions, even if it affects the player inventory. After the window is closed, a number of these packets are sent to update the player's inventory window (0). ");
        jsonObject.addProperty("stateID", "The last received State ID from either a Set Container Slot or a Set Container Content packet ");
        jsonObject.addProperty("slot", "The slot that should be updated");
        jsonObject.addProperty("slotData", "The new item stack for the slot. If the item stack is empty, it removes the item from the slot. ");
        return jsonObject;
    }

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
