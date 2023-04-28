package de.ari24.packetlogger.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import de.ari24.packetlogger.PacketLogger;
import de.ari24.packetlogger.config.PacketLoggerConfigModel;
import de.ari24.packetlogger.ui.PacketLoggerToast;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.client.toast.Toast;
import net.minecraft.network.packet.Packet;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ToggleLogging {
    public static LiteralArgumentBuilder<FabricClientCommandSource> register() {
        return ClientCommandManager.literal("logging")
                .then(ClientCommandManager.literal("off").executes(ctx -> {
                    executeChange(PacketLoggerConfigModel.LogState.OFF);
                    return 1;
                }))
                .then(ClientCommandManager.literal("on").executes(ctx -> {
                    executeChange(PacketLoggerConfigModel.LogState.LOGGING);
                    return 1;
                }));
    }

    private static void executeChange(PacketLoggerConfigModel.LogState newState) {
        PacketLogger.CONFIG.logState(newState);
        PacketLogger.LOGGER.info("Logging packets " + newState.name().toLowerCase());
        PacketLoggerToast.notify("Logging state: " + newState.name().toLowerCase());
    }
}
