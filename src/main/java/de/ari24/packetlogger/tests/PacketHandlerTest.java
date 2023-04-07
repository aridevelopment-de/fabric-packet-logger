package de.ari24.packetlogger.tests;

import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.packets.PacketHandler;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.NetworkState;
import net.minecraft.network.packet.Packet;

import java.util.List;
import java.util.Map;

class PacketHandlerTest {

    @org.junit.jupiter.api.Test
    void testPlayPackets() {
        Map<Class<? extends Packet<?>>, BasePacketHandler<?>> handlers = PacketHandler.getHandlers();
        List<Class<? extends Packet<?>>> implementedPackets = handlers.keySet().stream().toList();

        Int2ObjectMap<Class<? extends Packet<?>>> registeredPlayPacketsMapping = NetworkState.PLAY.getPacketIdToPacketMap(NetworkSide.CLIENTBOUND);
        List<Class<? extends Packet<?>>> registeredPlayPackets = registeredPlayPacketsMapping.values().stream().toList();
        compareAndPrintResults(registeredPlayPackets, implementedPackets);
    }

    @org.junit.jupiter.api.Test
    void testLoginPackets() {
        Map<Class<? extends Packet<?>>, BasePacketHandler<?>> handlers = PacketHandler.getHandlers();
        List<Class<? extends Packet<?>>> implementedPackets = handlers.keySet().stream().toList();

        Int2ObjectMap<Class<? extends Packet<?>>> registeredPlayPacketsMapping = NetworkState.LOGIN.getPacketIdToPacketMap(NetworkSide.CLIENTBOUND);
        List<Class<? extends Packet<?>>> registeredPlayPackets = registeredPlayPacketsMapping.values().stream().toList();
        compareAndPrintResults(registeredPlayPackets, implementedPackets);
    }

    @org.junit.jupiter.api.Test
    void testStatusPackets() {
        Map<Class<? extends Packet<?>>, BasePacketHandler<?>> handlers = PacketHandler.getHandlers();
        List<Class<? extends Packet<?>>> implementedPackets = handlers.keySet().stream().toList();

        Int2ObjectMap<Class<? extends Packet<?>>> registeredPlayPacketsMapping = NetworkState.STATUS.getPacketIdToPacketMap(NetworkSide.CLIENTBOUND);
        List<Class<? extends Packet<?>>> registeredPlayPackets = registeredPlayPacketsMapping.values().stream().toList();
        compareAndPrintResults(registeredPlayPackets, implementedPackets);
    }

    @org.junit.jupiter.api.Test
    void testHandshakingPackets() {
        Map<Class<? extends Packet<?>>, BasePacketHandler<?>> handlers = PacketHandler.getHandlers();
        List<Class<? extends Packet<?>>> implementedPackets = handlers.keySet().stream().toList();

        Int2ObjectMap<Class<? extends Packet<?>>> registeredPlayPacketsMapping = NetworkState.HANDSHAKING.getPacketIdToPacketMap(NetworkSide.CLIENTBOUND);
        List<Class<? extends Packet<?>>> registeredPlayPackets = registeredPlayPacketsMapping.values().stream().toList();
        compareAndPrintResults(registeredPlayPackets, implementedPackets);
    }

    void compareAndPrintResults(List<Class<? extends Packet<?>>> registeredPackets, List<Class<? extends Packet<?>>> implementedPackets) {
        // now compare the two lists
        List<Class<? extends Packet<?>>> missing = registeredPackets.stream().filter(p -> !implementedPackets.contains(p)).toList();

        // print
        System.out.println("Missing packets: " + missing.size() + " packets");
        for (Class<? extends Packet<?>> packet : missing) {
            System.out.println(packet.getName());
        }

        // print
        /*System.out.println("\n=========================================");
        System.out.println("Implemented packets: " + implementedPackets.size() + " packets");
        for (Class<? extends Packet<?>> packet : implementedPackets) {
            System.out.println(packet.getName());
        }*/

        // assert
        assert missing.isEmpty();
    }
}