package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.SelectAdvancementTabS2CPacket;

public class SelectAdvancementTabS2CPacketHandler implements BasePacketHandler<SelectAdvancementTabS2CPacket> {


    @Override
    public JsonObject serialize(SelectAdvancementTabS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("hasId", packet.getTabId() != null);

        if (packet.getTabId() != null) {
            jsonObject.addProperty("tabId", packet.getTabId().toString());
        }

        return jsonObject;
    }
}
