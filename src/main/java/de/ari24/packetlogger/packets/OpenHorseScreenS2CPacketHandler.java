package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.OpenHorseScreenS2CPacket;

public class OpenHorseScreenS2CPacketHandler implements BasePacketHandler<OpenHorseScreenS2CPacket> {
    @Override
    public String name() {
        return "OpenHorseScreen";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Open_Horse_Screen";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet is used exclusively for opening the horse GUI. Open Screen is used for all other GUIs. The client will not open the inventory if the Entity ID does not point to an horse-like animal. ");
        jsonObject.addProperty("wikiVgNotes", "Own notes: An extra packet is being used for horses/llamas/donkeys/mules here to get the entity id. This is important to render the entity itself in the inventory.");
        jsonObject.addProperty("windowId", "The window ID that should be used when sending the Window Items packet.");
        jsonObject.addProperty("slotCount", "The number of slots in the inventory window.");
        jsonObject.addProperty("entityId", "The entity ID of the horse-like animal.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(OpenHorseScreenS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("windowId", packet.getSyncId());
        jsonObject.addProperty("slotCount", packet.getSlotCount());
        ConvertUtils.appendEntity(jsonObject, packet.getHorseId());
        return jsonObject;
    }
}
