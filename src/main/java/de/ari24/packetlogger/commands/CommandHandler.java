package de.ari24.packetlogger.commands;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

public class CommandHandler {
    public static void registerClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("packetlogger")
                    .then(ToggleLogging.register())
                    .then(OpenWebUi.register())
                    .then(ExportData.register())
            );
        });
    }
}
