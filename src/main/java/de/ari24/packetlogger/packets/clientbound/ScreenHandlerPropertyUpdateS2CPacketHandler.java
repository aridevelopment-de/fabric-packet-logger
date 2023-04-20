package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.ScreenHandlerPropertyUpdateS2CPacket;

public class ScreenHandlerPropertyUpdateS2CPacketHandler implements BasePacketHandler<ScreenHandlerPropertyUpdateS2CPacket> {
    @Override
    public String name() {
        return "SetContainerProperty";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Set_Container_Property";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet is used to inform the client that part of a GUI window should be updated");
        jsonObject.addProperty("wikiVgNotes", "The meaning of the Property field depends on the type of the window. A table with all window types & values can be found here: https://wiki.vg/Protocol#Set_Container_Property");
        jsonObject.addProperty("windowId", "The ID of the window which should be updated");
        jsonObject.addProperty("property", "The property which should be updated");
        jsonObject.addProperty("value", "The new value of the property");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(ScreenHandlerPropertyUpdateS2CPacket packet) {
        // TODO: Add table data based on inventory type
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("windowId", packet.getSyncId());
        jsonObject.addProperty("property", packet.getPropertyId());
        jsonObject.addProperty("value", packet.getValue());
        return jsonObject;
    }
}
