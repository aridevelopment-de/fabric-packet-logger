package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.UpdateSelectedSlotS2CPacket;

public class UpdateSelectedSlotS2CPacketHandler implements BasePacketHandler<UpdateSelectedSlotS2CPacket> {
    @Override
    public String name() {
        return "SetHeldItem";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Set_Held_Item";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sent to change the player's slot selection.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("slot", "The slot which the player has selected (0-8)");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(UpdateSelectedSlotS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("slot", packet.getSlot());
        return jsonObject;
    }
}

