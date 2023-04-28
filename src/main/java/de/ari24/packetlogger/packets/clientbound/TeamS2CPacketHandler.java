package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.TeamS2CPacket;

public class TeamS2CPacketHandler implements BasePacketHandler<TeamS2CPacket> {

    private JsonObject serializeTeam(TeamS2CPacket.SerializableTeam team) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("displayName", team.getDisplayName().toString());
        jsonObject.addProperty("prefix", team.getPrefix().toString());
        jsonObject.addProperty("suffix", team.getSuffix().toString());
        jsonObject.addProperty("nameTagVisibility", team.getNameTagVisibilityRule());
        jsonObject.addProperty("collisionRule", team.getCollisionRule());
        jsonObject.addProperty("color", team.getColor().getName());
        jsonObject.addProperty("friendlyFlags", team.getFriendlyFlagsBitwise());
        jsonObject.addProperty("allowFriendlyFire", (team.getFriendlyFlagsBitwise() & 0x01) != 0);
        jsonObject.addProperty("canSeeFriendlyInvisibles", (team.getFriendlyFlagsBitwise() & 0x02) != 0);
        return jsonObject;
    }

    @Override
    public JsonObject serialize(TeamS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("teamName", packet.getTeamName());

        if (packet.getTeamOperation() != null) {
            jsonObject.addProperty("mode", packet.getTeamOperation().name());
        } else {
            jsonObject.addProperty("mode", "not known");
        }

        jsonObject.add("playerNames", ConvertUtils.GSON_INSTANCE.toJsonTree(packet.getPlayerNames()));

        if (packet.getTeam().isPresent()) {
            jsonObject.add("team", serializeTeam(packet.getTeam().get()));
        }

        return jsonObject;
    }
}
