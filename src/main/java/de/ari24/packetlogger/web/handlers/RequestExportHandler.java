package de.ari24.packetlogger.web.handlers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.PacketLogger;
import de.ari24.packetlogger.commands.ExportData;
import de.ari24.packetlogger.ui.PacketLoggerToast;
import org.java_websocket.WebSocket;

import java.util.List;

public class RequestExportHandler implements BaseHandler {
    @Override
    public void handle(WebSocket ws, JsonObject data) {
        try {
            ExportData.exportData();
        } catch (Exception e) {
            PacketLoggerToast.notify("An error occurred while retrieving packet details. Please check your console for more detail!");
            PacketLogger.LOGGER.error("An error occurred while retrieving packet details!", e);
        }
    }
}
