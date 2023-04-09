package de.ari24.packetlogger;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.serializer.PacketSerializer;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.ConnectionProtocol;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.game.ClientboundBundlePacket;
import net.minecraft.network.protocol.game.ClientboundRotateHeadPacket;
import net.minecraft.network.protocol.status.ClientboundPongResponsePacket;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

public class PacketHandler {
    public static <T extends Packet<?>> void handlePacket(T packet, PacketFlow packetFlow) {
        if (packetFlow == PacketFlow.SERVERBOUND) return;
        if (packet instanceof ClientboundBundlePacket bundlePacket) {
            bundlePacket.subPackets().forEach((p1) -> PacketHandler.handlePacket(p1, packetFlow));
            return;
        }

        JsonObject jsonObject = new JsonObject();
        ConnectionProtocol protocol = Objects.requireNonNull(ConnectionProtocol.getProtocolForPacket(packet));
        int id = protocol.getPacketId(PacketFlow.CLIENTBOUND, packet);

        // used for frontend-side differentiation
        jsonObject.addProperty("type", "packet");
        jsonObject.addProperty("legacyName", packet.getClass().getSimpleName());
        jsonObject.addProperty("id", protocol.name() + "-0x" + StringUtils.leftPad(Integer.toHexString(id), 2, "0").toUpperCase(Locale.ROOT));

        try {
            System.out.println(" ");
            System.out.println(packet.getClass().getSimpleName());
            System.out.println(PacketSerializer.serializePacket(packet));
        } catch (Exception e) {
            PacketLogger.LOGGER.error("Error while serializing packet " + packet.getClass().getSimpleName());
            PacketLogger.LOGGER.error(e.toString());
        }

        PacketLogger.wss.sendAll(jsonObject);
    }

    /*private static String getPacketId(Class<? extends Packet<?>> packetClass) {
        NetworkState state = NetworkState.HANDLER_STATE_MAP.get(packetClass);
        NetworkState.PacketHandler<?> packetHandler = state.packetHandlers.get(NetworkSide.CLIENTBOUND);
        int id = packetHandler.getId(packetClass);
        return state.name() + "-0x" + StringUtils.leftPad(Integer.toHexString(id), 2, "0").toUpperCase(Locale.ROOT);
    }*/
}
