package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.AdvancementTabC2SPacket;

public class AdvancementTabC2SPacketHandler implements BasePacketHandler<AdvancementTabC2SPacket> {
    @Override
    public JsonObject serialize(AdvancementTabC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", packet.getAction().ordinal());

        if (packet.getTabToOpen() != null) {
            jsonObject.addProperty("tabId", packet.getTabToOpen().toString());
        }

        return jsonObject;
    }
}
