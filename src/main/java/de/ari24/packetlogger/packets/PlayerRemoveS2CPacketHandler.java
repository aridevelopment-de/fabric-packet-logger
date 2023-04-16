package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.PlayerRemoveS2CPacket;

public class PlayerRemoveS2CPacketHandler implements BasePacketHandler<PlayerRemoveS2CPacket> {
    @Override
    public String name() {
        return "PlayerInfoRemove";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Player_Info_Remove";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Used by the server to remove players from the player list.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("numPlayers", "The number of players to remove from the player list.");
        jsonObject.addProperty("players", "UUIDs of players to remove.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(PlayerRemoveS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("numPlayers", packet.profileIds().size());
        jsonObject.add("players", ConvertUtils.GSON_INSTANCE.toJsonTree(packet.profileIds()));
        return jsonObject;
    }
}
