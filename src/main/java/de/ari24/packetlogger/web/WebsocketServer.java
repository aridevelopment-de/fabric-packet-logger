package de.ari24.packetlogger.web;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.PacketLogger;
import de.ari24.packetlogger.config.PacketLoggerConfigModel;
import de.ari24.packetlogger.packets.PacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import de.ari24.packetlogger.web.handlers.BaseHandler;
import de.ari24.packetlogger.web.handlers.PacketloggerLogstateHandler;
import de.ari24.packetlogger.web.handlers.RequestMcPacketInfoHandler;
import de.ari24.packetlogger.web.handlers.WSSPacket;
import lombok.Getter;
import org.java_websocket.WebSocket;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.*;

public class WebsocketServer extends WebSocketServer {

    @Getter
    private final Collection<WebSocket> clients;

    private final Map<Integer, Class<? extends BaseHandler>> handlers = Map.of(
            WSSPacket.PACKETLOGGER_LOGSTATE.ordinal(), PacketloggerLogstateHandler.class,
            WSSPacket.REQUEST_MC_PACKET_INFO.ordinal(), RequestMcPacketInfoHandler.class
    );

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
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", WSSPacket.PACKETLOGGER_LOGSTATE.ordinal());
            jsonObject.addProperty("data", PacketLogger.CONFIG.logState().ordinal());
            conn.send(jsonObject.toString());
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

        int id = jsonObject.get("id").getAsInt();

        if (!handlers.containsKey(id)) {
            PacketLogger.LOGGER.error("Unknown web packet received from websocket id " + id);
            return;
        }

        try {
            BaseHandler handler = handlers.get(id).getDeclaredConstructor().newInstance();
            handler.handle(conn, jsonObject);
        } catch (Exception exception) {
            PacketLogger.LOGGER.error("Failed to handle web packet from websocket id " + id, exception);
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
        String serializedData = object.toString();

        for (WebSocket client : clients) {
            client.send(serializedData);
        }
    }
}
