package de.ari24.packetlogger.web.handlers;

import com.google.gson.JsonObject;
import org.java_websocket.WebSocket;

public interface BaseHandler {
    void handle(WebSocket ws, JsonObject data);
}
