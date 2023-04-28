package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.CooldownUpdateS2CPacket;

public class CooldownUpdateS2CPacketHandler implements BasePacketHandler<CooldownUpdateS2CPacket> {
    @Override
    public JsonObject serialize(CooldownUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("item", ConvertUtils.serializeItem(packet.getItem()));
        jsonObject.addProperty("cooldownTicks", packet.getCooldown());
        return jsonObject;
    }
}
