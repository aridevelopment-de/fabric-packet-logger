package de.ari24.packetlogger.web.handlers;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.PacketHandler;
import org.java_websocket.WebSocket;

public class RequestMcPacketInfoHandler implements BaseHandler {
    @Override
    public void handle(WebSocket ws, JsonObject data) {
        int index = data.get("data").getAsInt();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", WSSPacket.MC_PACKET_INFO.ordinal());
        jsonObject.add("data", PacketHandler.retrievePacketDetails(index));
        ws.send(jsonObject.toString());
    }
}
