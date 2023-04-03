package de.ari24.packetlogger.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import de.ari24.packetlogger.PacketLogger;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ToggleLogging {
    public static LiteralArgumentBuilder<FabricClientCommandSource> register() {
        return ClientCommandManager.literal("toggleLogging")
                .executes(ctx -> {
                    PacketLogger.CONFIG.logPackets(!PacketLogger.CONFIG.logPackets());

                    if (PacketLogger.CONFIG.logPackets()) {
                        PacketLogger.LOGGER.info("Logging packets enabled");
                        MinecraftClient.getInstance().player.sendMessage(Text.literal("Logging packets enabled"), true);
                    } else {
                        PacketLogger.LOGGER.info("Logging packets disabled");
                        MinecraftClient.getInstance().player.sendMessage(Text.literal("Logging packets disabled"), true);
                    }

                    return 1;
                });
    }
}
