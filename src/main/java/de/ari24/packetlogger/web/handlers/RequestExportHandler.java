package de.ari24.packetlogger.web.handlers;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.commands.ExportData;
import org.java_websocket.WebSocket;

public class RequestExportHandler implements BaseHandler {
    @Override
    public void handle(WebSocket ws, JsonObject data) {
        ExportData.exportData();
    }
}
