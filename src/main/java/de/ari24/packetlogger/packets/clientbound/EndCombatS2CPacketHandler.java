package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.EndCombatS2CPacketAccessor;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.EndCombatS2CPacket;

public class EndCombatS2CPacketHandler implements BasePacketHandler<EndCombatS2CPacket> {

    @Override
    public JsonObject serialize(EndCombatS2CPacket packet) {
        EndCombatS2CPacketAccessor accessor = (EndCombatS2CPacketAccessor) packet;
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, accessor.getAttackerId(), "attacker", "attackerId");
        jsonObject.addProperty("duration", accessor.getTimeSinceLastAttack());
        return jsonObject;
    }
}
