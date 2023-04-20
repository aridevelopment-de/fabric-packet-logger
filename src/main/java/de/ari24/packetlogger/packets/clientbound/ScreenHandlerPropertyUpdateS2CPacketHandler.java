package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.ScreenHandlerPropertyUpdateS2CPacket;

public class ScreenHandlerPropertyUpdateS2CPacketHandler implements BasePacketHandler<ScreenHandlerPropertyUpdateS2CPacket> {


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
