package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket;

public class DeathMessageS2CPacketHandler implements BasePacketHandler<DeathMessageS2CPacket> {
    @Override
    public String name() {
        return "CombatDeath";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Combat_Death";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Used to send a respawn screen. This data was once used for twitch.tv metadata circa 1.8.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("player", "The player that died (should match the client's entity ID)");
        jsonObject.addProperty("playerId", "The player that died (should match the client's entity ID)");
        jsonObject.addProperty("killer", "The killer entity's ID, or -1 if there is no obvious killer.");
        jsonObject.addProperty("killerId", "The killer entity's ID, or -1 if there is no obvious killer.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(DeathMessageS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.getEntityId(), "player", "playerId");
        ConvertUtils.appendEntity(jsonObject, packet.getKillerId(), "killer", "killerId");
        jsonObject.addProperty("message", packet.getMessage().toString());
        return jsonObject;
    }
}
