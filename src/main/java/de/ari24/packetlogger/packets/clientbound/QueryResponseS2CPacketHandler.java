package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.query.QueryResponseS2CPacket;
import net.minecraft.server.ServerMetadata;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class QueryResponseS2CPacketHandler implements BasePacketHandler<QueryResponseS2CPacket> {


    private JsonObject getPlayerInfo(ServerMetadata.Players players) {
        JsonObject json = new JsonObject();
        json.addProperty("max", players.max());
        json.addProperty("online", players.online());

        List<JsonObject> samples = new ArrayList<>();
        players.sample().forEach(sample -> samples.add(ConvertUtils.serializeGameProfile(sample)));

        json.add("sample", ConvertUtils.GSON_INSTANCE.toJsonTree(samples));
        return json;
    }

    private JsonObject getVersionInfo(ServerMetadata.Version version) {
        JsonObject json = new JsonObject();
        json.addProperty("name", version.gameVersion());
        json.addProperty("protocol", version.protocolVersion());
        return json;
    }

    private JsonObject getFaviconInfo(ServerMetadata.Favicon favicon) {
        JsonObject json = new JsonObject();
        String faviconString = new String(Base64.getEncoder().encode(favicon.iconBytes()), StandardCharsets.UTF_8);
        json.addProperty("icon", "data:image/png;base64," + faviconString);
        return json;
    }

    @Override
    public JsonObject serialize(QueryResponseS2CPacket packet) {
        JsonObject json = new JsonObject();
        json.addProperty("description", packet.metadata().description().toString());

        if (packet.metadata().players().isPresent()) {
            json.add("players", getPlayerInfo(packet.metadata().players().get()));
        } else {
            json.add("players", JsonNull.INSTANCE);
        }

        if (packet.metadata().version().isPresent()) {
            json.add("version", getVersionInfo(packet.metadata().version().get()));
        } else {
            json.add("version", JsonNull.INSTANCE);
        }

        if (packet.metadata().favicon().isPresent()) {
            json.add("favicon", getFaviconInfo(packet.metadata().favicon().get()));
        } else {
            json.add("favicon", JsonNull.INSTANCE);
        }

        return json;
    }
}
