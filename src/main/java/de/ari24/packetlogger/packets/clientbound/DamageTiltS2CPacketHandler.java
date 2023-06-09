package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.DamageTiltS2CPacket;

public class DamageTiltS2CPacketHandler implements BasePacketHandler<DamageTiltS2CPacket> {

    @Override
    public JsonObject serialize(DamageTiltS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.id());
        jsonObject.addProperty("yaw", packet.yaw());
        return jsonObject;
    }
}
