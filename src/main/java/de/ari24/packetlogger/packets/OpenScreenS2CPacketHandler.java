package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.OpenScreenS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class OpenScreenS2CPacketHandler implements BasePacketHandler<OpenScreenS2CPacket> {
    @Override
    public String name() {
        return "OpenScreen";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Open_Screen";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This is sent to the client when it should open an inventory, such as a chest, workbench, furnace, or other container. This message is not sent anywhere for clients opening their own inventory. Resending this packet with already existing window id, will update the window title and window type without closing the window. For horses, use https://wiki.vg/index.php?title=Protocol&oldid=18067#Open_Horse_Screen");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("windowId", "A unique id number for the window to be displayed. Notchian server implementation is a counter, starting at 1. ");
        jsonObject.addProperty("windowType", "The type of window to be displayed. Contained in the minecraft:menu registry; see https://wiki.vg/Inventory for the different values");
        jsonObject.addProperty("windowTitle", "The title of the window.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(OpenScreenS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("windowId", packet.getSyncId());

        if (packet.getScreenHandlerType() != null) {
            Identifier id = Registries.SCREEN_HANDLER.getId(packet.getScreenHandlerType());

            if (id != null) {
                jsonObject.addProperty("windowType", id.toString());
            }
        }

        jsonObject.addProperty("windowTitle", packet.getName().toString());
        return jsonObject;
    }
}
