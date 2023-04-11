package de.ari24.packetlogger.web;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.PacketLogger;
import de.ari24.packetlogger.packets.PacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import lombok.Getter;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class WebsocketServer extends WebSocketServer {

    @Getter
    private final Collection<WebSocket> clients;

    public WebsocketServer(int port) {
        super(new InetSocketAddress(port));
        super.setReuseAddr(true);

        this.clients = new HashSet<>();
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        PacketLogger.LOGGER.info("Client connected, sending meta");
        clients.add(conn);

        try {
            JsonObject jsonObject = new JsonObject();;
            jsonObject.addProperty("type", "init");
            jsonObject.add("allPackets", ConvertUtils.GSON_INSTANCE.toJsonTree(PacketHandler.getRegisteredPacketIds()));
            jsonObject.add("descriptions", ConvertUtils.GSON_INSTANCE.toJsonTree(PacketHandler.getPacketDescriptions()));
            conn.send(jsonObject.toString());

            JsonObject loggingState = new JsonObject();
            loggingState.addProperty("type", "loggingState");
            loggingState.addProperty("state", PacketLogger.CONFIG.logPackets() ? "logging" : "off");
            conn.send(loggingState.toString());
        } catch (Exception exception) {
            PacketLogger.LOGGER.error("Failed to send meta to client", exception);
        }
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        PacketLogger.LOGGER.info("Client disconnected");
        clients.remove(conn);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        JsonObject jsonObject = ConvertUtils.GSON_INSTANCE.fromJson(message, JsonObject.class);
        String type = jsonObject.get("type").getAsString();

        if (Objects.equals(type, "loggingState")) {
            PacketLogger.CONFIG.logPackets(Objects.equals(jsonObject.get("state").getAsString(), "logging"));
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        PacketLogger.LOGGER.error("Websocket error", ex);
    }

    @Override
    public void onStart() {
        PacketLogger.LOGGER.info("Started websocket server on port " + this.getPort());
    }

    public void sendAll(JsonObject object) {
        for (WebSocket client : clients) {
            client.send(object.toString());
        }
    }
}
