package de.ari24.packetlogger.web;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.PacketLogger;
import de.ari24.packetlogger.packets.PacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashSet;

public class WebsocketServer extends WebSocketServer {

    private final Collection<WebSocket> clients;

    public WebsocketServer(int port) {
        super(new InetSocketAddress(port));
        super.setReuseAddr(true);

        this.clients = new HashSet<>();
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        clients.add(conn);

        JsonObject jsonObject = new JsonObject();;
        jsonObject.add("allPackets", ConvertUtils.GSON_INSTANCE.toJsonTree(PacketHandler.getRegisteredPacketIds()));
        conn.send(jsonObject.toString());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        clients.remove(conn);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {}

    @Override
    public void onError(WebSocket conn, Exception ex) {

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
