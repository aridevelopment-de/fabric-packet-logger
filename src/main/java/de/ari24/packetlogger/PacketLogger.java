package de.ari24.packetlogger;

import de.ari24.packetlogger.commands.CommandHandler;
import de.ari24.packetlogger.config.PacketLoggerConfig;
import de.ari24.packetlogger.web.HTTPServer;
import de.ari24.packetlogger.web.WebsocketServer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class PacketLogger implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("packetlogger");
    public static final PacketLoggerConfig CONFIG = PacketLoggerConfig.createAndLoad();
    public static WebsocketServer wss;

    @Override
    public void onInitialize() {
        LOGGER.info("Starting packet logger...");
        CommandHandler.registerClient();

        try {
            HTTPServer.start(CONFIG.webserverPort());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        wss = new WebsocketServer(CONFIG.wssPort());
        wss.start();
        LOGGER.info("Packet logger started!");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.info("Stopping packet logger...");

            try {
                wss.stop(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("Packet logger stopped!");
        }));
    }
}
