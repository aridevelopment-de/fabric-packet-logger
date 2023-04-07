package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.EndCombatS2CPacketAccessor;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.EndCombatS2CPacket;

public class EndCombatS2CPacketHandler implements BasePacketHandler<EndCombatS2CPacket> {
    @Override
    public String name() {
        return "EndCombat";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#End_Combat";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Unused by the Notchian client. This data was once used for twitch.tv metadata circa 1.8.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("attackerId", "ID of the primary opponent of the ended combat, or -1 if there is no obvious primary opponent.");
        jsonObject.addProperty("duration", "Length of the combat in ticks (rather time since last attack).");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(EndCombatS2CPacket packet) {
        EndCombatS2CPacketAccessor accessor = (EndCombatS2CPacketAccessor) packet;
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, accessor.getAttackerId(), "attacker", "attackerId");
        jsonObject.addProperty("duration", accessor.getTimeSinceLastAttack());
        return jsonObject;
    }
}
