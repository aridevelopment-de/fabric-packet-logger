package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.TeamS2CPacket;

public class TeamS2CPacketHandler implements BasePacketHandler<TeamS2CPacket> {
    @Override
    public String name() {
        return "UpdateTeams";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Update_Teams";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Creates and updates teams");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("teamName", "A unique name for the team (max 16 characters & shared with scoreboard).");
        jsonObject.addProperty("mode", "Determines layout of the packet");
        jsonObject.addProperty("playerNames", "List of players to add/remove from the team");
        jsonObject.addProperty("team", "Depending on the mode parameter, this team is used for various actions.");
        jsonObject.addProperty("team.displayName", "");
        jsonObject.addProperty("team.prefix", "");
        jsonObject.addProperty("team.suffix", "");
        jsonObject.addProperty("team.nameTagVisibility", "");
        jsonObject.addProperty("team.collisionRule", "Players normally collide and push each other when not being in the same team. This option controls this behaviour.");
        jsonObject.addProperty("team.color", "Color of names. Colors can also be defined in the prefix/suffix/displayName");
        jsonObject.addProperty("team.friendlyFlags", "");
        jsonObject.addProperty("team.allowFriendlyFire", "");
        jsonObject.addProperty("team.canSeeFriendlyInvisibles", "If true: can see invisible players on same team. ");
        return jsonObject;
    }

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
