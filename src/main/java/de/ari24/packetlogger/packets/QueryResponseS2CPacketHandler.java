package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.query.QueryResponseS2CPacket;
import net.minecraft.server.ServerMetadata;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class QueryResponseS2CPacketHandler implements BasePacketHandler<QueryResponseS2CPacket> {

    @Override
    public String name() {
        return "StatusResponse";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Status_Response";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Will be sent after the client sent a Ping Request packet. This contains information about the server itself.");
        jsonObject.addProperty("wikiVgNotes", """
                After receiving the Response packet, the client may send the next packet to help calculate the server's latency, or if it is only interested in the above information it can disconnect here.
                If the client does not receive a properly formatted response, then it will instead attempt a legacy ping.\s
                - See https://wiki.vg/Server_List_Ping#Status_Response for more information
                """);
        jsonObject.addProperty("description", "Server MOTD");
        jsonObject.addProperty("players.max", "Maximum of players");
        jsonObject.addProperty("players.online", "Current players online");
        jsonObject.addProperty("players.sample", "List of players (not all). An id of a sample needs to be a valid UUID format, else the connection will abort. ");
        jsonObject.addProperty("version.name", "Readable version name (e.g. 1.19.4)");
        jsonObject.addProperty("version.protocol", "Protocol version (e.g. 761)");
        jsonObject.addProperty("favicon", "The favicon should be a PNG image that is Base64 encoded (without newlines: \\n, new lines no longer work since 1.13) and prepended with data:image/png;base64,. It should also be noted that the source image must be exactly 64x64 pixels, otherwise the Notchian client will not render the image. ");
        return jsonObject;
    }

    private JsonObject getPlayerInfo(ServerMetadata.Players players) {
        JsonObject json = new JsonObject();
        json.addProperty("max", players.getPlayerLimit());
        json.addProperty("online", players.getOnlinePlayerCount());

        List<JsonObject> samples = new ArrayList<>();
        Arrays.stream(players.getSample()).toList().forEach(sample -> samples.add(ConvertUtils.serializeGameProfile(sample)));

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
