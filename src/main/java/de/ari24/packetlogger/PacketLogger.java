package de.ari24.packetlogger;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.commands.CommandHandler;
import de.ari24.packetlogger.config.PacketLoggerConfig;
import de.ari24.packetlogger.config.PacketLoggerConfigModel;
import de.ari24.packetlogger.packets.PacketHandler;
import de.ari24.packetlogger.utils.PacketTicker;
import de.ari24.packetlogger.web.HTTPServer;
import de.ari24.packetlogger.web.WebsocketServer;
import de.ari24.packetlogger.web.handlers.WSSPacket;
import io.wispforest.owo.config.Option;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

public class    PacketLogger implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("packetlogger");
    public static final PacketLoggerConfig CONFIG = PacketLoggerConfig.createAndLoad();
    public static final PacketTicker PACKET_TICKER = new PacketTicker();
    public static WebsocketServer wss;

    @Override
    public void onInitialize() {
        LOGGER.info("Starting packet logger...");
        CommandHandler.registerClient();

        Objects.requireNonNull(CONFIG.optionForKey(new Option.Key("logState"))).observe(t -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", WSSPacket.PACKETLOGGER_LOGSTATE.ordinal());
            jsonObject.addProperty("data", ((PacketLoggerConfigModel.LogState) t).ordinal());
            PacketLogger.wss.sendAll(jsonObject);
        });

        try {
            HTTPServer.start(CONFIG.webserverPort());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        wss = new WebsocketServer(CONFIG.wssPort());
        wss.start();
        PacketHandler.initialize();

        LOGGER.info("Packet logger started!");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.info("Stopping packet logger...");

            PacketHandler.cleanup();

            try {
                wss.stop(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("Packet logger stopped!");
        }));
    }
}
