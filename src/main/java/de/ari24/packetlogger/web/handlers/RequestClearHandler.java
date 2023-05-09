package de.ari24.packetlogger.web.handlers;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.PacketHandler;
import org.java_websocket.WebSocket;

public class RequestClearHandler implements BaseHandler {
    @Override
    public void handle(WebSocket ws, JsonObject data) {
        PacketHandler.getPacketData().clear();
    }
}
