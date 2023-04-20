package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.EnterCombatS2CPacket;

public class EnterCombatS2CPacketHandler implements BasePacketHandler<EnterCombatS2CPacket> {

    @Override
    public JsonObject serialize(EnterCombatS2CPacket packet) {
        return new JsonObject();
    }
}
