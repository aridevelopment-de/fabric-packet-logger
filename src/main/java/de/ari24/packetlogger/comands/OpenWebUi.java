package de.ari24.packetlogger.comands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import de.ari24.packetlogger.PacketLogger;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.util.Util;

public class OpenWebUi {
    public static LiteralArgumentBuilder<FabricClientCommandSource> register() {
        return ClientCommandManager.literal("open")
                .executes(ctx -> {
                    int httpPort = PacketLogger.CONFIG.webserverPort();
                    int wssPort = PacketLogger.CONFIG.wssPort();

                    String url = "http://localhost:" + httpPort + "index.html?wssPort=" + wssPort;
                    Util.getOperatingSystem().open(url);
                    return 1;
                });
    }
}
