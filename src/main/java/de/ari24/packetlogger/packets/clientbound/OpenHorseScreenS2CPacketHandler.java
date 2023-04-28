package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.OpenHorseScreenS2CPacket;

public class OpenHorseScreenS2CPacketHandler implements BasePacketHandler<OpenHorseScreenS2CPacket> {

    @Override
    public JsonObject serialize(OpenHorseScreenS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("windowId", packet.getSyncId());
        jsonObject.addProperty("slotCount", packet.getSlotCount());
        ConvertUtils.appendEntity(jsonObject, packet.getHorseId());
        return jsonObject;
    }
}
