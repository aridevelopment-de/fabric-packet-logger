package de.ari24.packetlogger.web.handlers;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.PacketLogger;
import de.ari24.packetlogger.packets.PacketHandler;
import de.ari24.packetlogger.ui.PacketLoggerToast;
import org.java_websocket.WebSocket;

public class RequestMcPacketInfoHandler implements BaseHandler {
    @Override
    public void handle(WebSocket ws, JsonObject data) {
        int index = data.get("data").getAsInt();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", WSSPacket.MC_PACKET_INFO.ordinal());

        JsonObject bodyData = new JsonObject();
        bodyData.addProperty("index", index);

        try {
            bodyData.add("packetData", PacketHandler.retrievePacketDetails(index));
        } catch (Exception e) {
            PacketLoggerToast.notify("An error occurred while retrieving packet details. Please check your console for more detail!");
            PacketLogger.LOGGER.error("An error occurred while retrieving packet details!", e);
            PacketLogger.LOGGER.error("Details:");
            PacketLogger.LOGGER.error("- Packet-data Index: " + index);

            if (index >= 0 && index < PacketHandler.getPacketData().size()) {
                PacketHandler.PacketData packetData = PacketHandler.getPacketData().get(index);
                PacketLogger.LOGGER.error("- Packet Id: " + packetData.packetId());
                PacketLogger.LOGGER.error("- Network Side: " + packetData.side());
                PacketLogger.LOGGER.error("- Network State: " + packetData.state());
            }
        }

        jsonObject.add("data", bodyData);
        ws.send(jsonObject.toString());
    }
}
