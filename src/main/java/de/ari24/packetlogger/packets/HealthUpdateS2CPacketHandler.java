package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.HealthUpdateS2CPacket;

public class HealthUpdateS2CPacketHandler implements BasePacketHandler<HealthUpdateS2CPacket> {

    @Override
    public String name() {
        return "SetHealth";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Health";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sent by the server to set the health of the player it is sent to. ");
        jsonObject.addProperty("wikiVgNotes", "Food saturation acts as a food “overcharge”. Food values will not decrease while the saturation is over zero. New players logging in or respawning automatically get a saturation of 5.0. Eating food increases the saturation as well as the food bar. (https://minecraft.fandom.com/wiki/Food#Hunger_vs._Saturation)");
        jsonObject.addProperty("health", "0 or less = dead, 20 = full HP (if not modified by a plugin)");
        jsonObject.addProperty("food", "0 - 20. The player can only sprint if the food is greater than 6.");
        jsonObject.addProperty("saturation", "Seems to vary from 0.0 - 5.0 in integer increments.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(HealthUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("health", packet.getHealth());
        jsonObject.addProperty("food", packet.getFood());
        jsonObject.addProperty("saturation", packet.getSaturation());
        return jsonObject;
    }
}
