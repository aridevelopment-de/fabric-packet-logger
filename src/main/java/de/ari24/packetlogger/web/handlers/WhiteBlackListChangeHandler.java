package de.ari24.packetlogger.web.handlers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.PacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import org.java_websocket.WebSocket;

public class WhiteBlackListChangeHandler implements BaseHandler {
    @Override
    public void handle(WebSocket ws, JsonObject data) {
        JsonObject body = data.get("data").getAsJsonObject();
        JsonArray whitelist = body.get("whitelist").getAsJsonArray();
        JsonArray blacklist = body.get("blacklist").getAsJsonArray();

        PacketHandler.getWhitelist().clear();
        whitelist.forEach(jsonElement -> PacketHandler.getWhitelist().add(jsonElement.getAsString()));

        PacketHandler.getBlacklist().clear();
        blacklist.forEach(jsonElement -> PacketHandler.getBlacklist().add(jsonElement.getAsString()));

        // To confirm changes, send WhiteBlackListConfirm packet
        JsonObject response = new JsonObject();
        response.addProperty("id", WSSPacket.WHITE_BLACK_LIST_CONFIRM.ordinal());

        JsonObject responseBody = new JsonObject();
        responseBody.add("whitelist", ConvertUtils.GSON_INSTANCE.toJsonTree(PacketHandler.getWhitelist()));
        responseBody.add("blacklist", ConvertUtils.GSON_INSTANCE.toJsonTree(PacketHandler.getBlacklist()));
        response.add("data", responseBody);
        ws.send(response.toString());
    }
}
