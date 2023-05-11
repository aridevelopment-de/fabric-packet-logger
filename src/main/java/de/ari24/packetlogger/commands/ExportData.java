package de.ari24.packetlogger.commands;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import de.ari24.packetlogger.PacketLogger;
import de.ari24.packetlogger.config.PacketLoggerConfigModel;
import de.ari24.packetlogger.packets.PacketHandler;
import de.ari24.packetlogger.ui.PacketLoggerToast;
import de.ari24.packetlogger.utils.MinecraftUtils;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.NetworkState;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ExportData {
    public static LiteralArgumentBuilder<FabricClientCommandSource> register() {
        return ClientCommandManager.literal("export")
                .executes(ctx -> {
                    try {
                        exportData(List.of(), List.of());
                    } catch (Exception e) {
                        PacketLoggerToast.notify("An error occurred while retrieving packet details. Please check your console for more detail!");
                        PacketLogger.LOGGER.error("An error occurred while retrieving packet details!", e);
                    }
                    return 1;
                });
    }

    public static void exportData(List<String> whitelist, List<String> blacklist) throws Exception {
        // whitelist: ["cbound-play-0x4E"]
        PacketLogger.CONFIG.logState(PacketLoggerConfigModel.LogState.OFF);
        PacketLoggerToast.notify("Exporting packets. This might take a while...");

        JsonArray dump = PacketHandler.retrieveAllPacketDetails().stream().filter(packet -> {
            int id = packet.get("id").getAsInt();
            String direction = NetworkSide.values()[packet.get("direction").getAsInt()] == NetworkSide.CLIENTBOUND ? "cbound" : "sbound";
            String networkState = NetworkState.values()[packet.get("networkState").getAsInt()].name().toLowerCase();
            String formattedId = direction + "-" + networkState.toLowerCase() + "-" + MinecraftUtils.convertToPacketId(id);
            return (whitelist.size() > 0 && whitelist.contains(formattedId)) || (blacklist.size() > 0 && !blacklist.contains(formattedId)) || (whitelist.size() == 0 && blacklist.size() == 0);
        }).collect(JsonArray::new, JsonArray::add, JsonArray::addAll);

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
