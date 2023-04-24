package de.ari24.packetlogger.web.handlers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.PacketLogger;
import de.ari24.packetlogger.config.PacketLoggerConfigModel;
import de.ari24.packetlogger.packets.PacketHandler;
import de.ari24.packetlogger.ui.PacketLoggerToast;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.java_websocket.WebSocket;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class RequestExportHandler implements BaseHandler {
    @Override
    public void handle(WebSocket ws, JsonObject data) {
        PacketLogger.CONFIG.logState(PacketLoggerConfigModel.LogState.OFF);
        PacketLoggerToast.notify("Exporting packets. This might take a while...");
        JsonArray dump = PacketHandler.retrieveAllPacketDetails();

        // let filename be current date and time
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

        // making sure the directory exists
        new File(MinecraftClient.getInstance().runDirectory, PacketLogger.LOG_DIRECTORY).mkdirs();

        String fileName = PacketLogger.LOG_DIRECTORY + "/export-" + formatter.format(System.currentTimeMillis()) + ".json";
        File file = new File(MinecraftClient.getInstance().runDirectory, fileName);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(dump.toString());
            writer.close();
        } catch (IOException e) {
            PacketLoggerToast.notify("Failed to export packets. See console for more info.");
            throw new RuntimeException(e);
        }

        MutableText text = Text.literal("Packet log exported to ").append(Text.literal(file.getName()).formatted(Formatting.UNDERLINE).styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, file.getAbsolutePath()))));
        MinecraftClient.getInstance().getMessageHandler().onGameMessage(text, false);
        PacketLoggerToast.notify("Exported packets to " + fileName);
    }
}
