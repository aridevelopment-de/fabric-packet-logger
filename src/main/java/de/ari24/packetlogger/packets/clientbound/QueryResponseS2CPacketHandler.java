package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.query.QueryResponseS2CPacket;
import net.minecraft.server.ServerMetadata;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class QueryResponseS2CPacketHandler implements BasePacketHandler<QueryResponseS2CPacket> {


    private JsonObject getPlayerInfo(ServerMetadata.Players players) {
        JsonObject json = new JsonObject();
        json.addProperty("max", players.getPlayerLimit());
        json.addProperty("online", players.getOnlinePlayerCount());

        if (players.getSample() == null) {
            json.add("sample", JsonNull.INSTANCE);
            return json;
        }

        List<JsonObject> samples = new ArrayList<>();
        Arrays.stream(players.getSample()).forEach(sample -> samples.add(ConvertUtils.serializeGameProfile(sample)));

        json.add("sample", ConvertUtils.GSON_INSTANCE.toJsonTree(samples));
        return json;
    }

    private JsonObject getVersionInfo(ServerMetadata.Version version) {
        JsonObject json = new JsonObject();
        json.addProperty("name", version.getGameVersion());
        json.addProperty("protocol", version.getProtocolVersion());
        return json;
    }

    @Override
    public JsonObject serialize(QueryResponseS2CPacket packet) {
        JsonObject json = new JsonObject();

        if (packet.getServerMetadata().getDescription() != null) {
            json.addProperty("description", packet.getServerMetadata().getDescription().toString());
        } else {
            json.add("description", JsonNull.INSTANCE);
        }

        if (packet.getServerMetadata().getPlayers() != null) {
            json.add("players", getPlayerInfo(packet.getServerMetadata().getPlayers()));
        } else {
            json.add("players", JsonNull.INSTANCE);
        }

        if (packet.getServerMetadata().getVersion() != null) {
            json.add("version", getVersionInfo(packet.getServerMetadata().getVersion()));
        } else {
            json.add("version", JsonNull.INSTANCE);
        }

        if (packet.getServerMetadata().getFavicon() != null) {
            json.addProperty("favicon", packet.getServerMetadata().getFavicon());
        } else {
            json.add("favicon", JsonNull.INSTANCE);
        }

        return json;
    }
}
