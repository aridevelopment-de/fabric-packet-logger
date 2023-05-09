package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket;

public class DeathMessageS2CPacketHandler implements BasePacketHandler<DeathMessageS2CPacket> {

    @Override
    public JsonObject serialize(DeathMessageS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.getEntityId(), "player", "playerId");
        ConvertUtils.appendEntity(jsonObject, packet.getKillerId(), "killer", "killerId");
        jsonObject.addProperty("message", packet.getMessage().toString());
        return jsonObject;
    }
}
