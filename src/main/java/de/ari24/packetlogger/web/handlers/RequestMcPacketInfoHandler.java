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

        JsonObject bodyData = new JsonObject();
        bodyData.addProperty("index", index);
        bodyData.add("packetData", PacketHandler.retrievePacketDetails(index));
        jsonObject.add("data", bodyData);
        ws.send(jsonObject.toString());
    }
}
