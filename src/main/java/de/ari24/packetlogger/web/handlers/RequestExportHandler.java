package de.ari24.packetlogger.web.handlers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.commands.ExportData;
import org.java_websocket.WebSocket;

import java.util.List;

public class RequestExportHandler implements BaseHandler {
    @Override
    public void handle(WebSocket ws, JsonObject data) {
        List<String> whitelist = data.getAsJsonObject("data").get("whitelist").getAsJsonArray().asList().stream().map(JsonElement::getAsString).toList();
        List<String> blacklist = data.getAsJsonObject("data").get("blacklist").getAsJsonArray().asList().stream().map(JsonElement::getAsString).toList();

        ExportData.exportData(whitelist, blacklist);
    }
}
