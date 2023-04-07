package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.EnterCombatS2CPacket;

public class EnterCombatS2CPacketHandler implements BasePacketHandler<EnterCombatS2CPacket> {
    @Override
    public String name() {
        return "EnterCombat";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Enter_Combat";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Unused by the Notchian client. This data was once used for twitch.tv metadata circa 1.8.");
        jsonObject.addProperty("wikiVgNotes", "Information somewhat missing. Should probably get some information from back then"); // TODO
        return jsonObject;
    }

    @Override
    public JsonObject serialize(EnterCombatS2CPacket packet) {
        return new JsonObject();
    }
}
