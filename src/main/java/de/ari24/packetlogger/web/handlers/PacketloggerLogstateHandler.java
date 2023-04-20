package de.ari24.packetlogger.web.handlers;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.PacketLogger;
import de.ari24.packetlogger.config.PacketLoggerConfigModel;
import org.java_websocket.WebSocket;

public class PacketloggerLogstateHandler implements BaseHandler {
    @Override
    public void handle(WebSocket ws, JsonObject data) {
        // TODO: Add exception handling
        int state = data.get("data").getAsInt();
        PacketLogger.CONFIG.logState(PacketLoggerConfigModel.LogState.values()[state]);
    }
}
