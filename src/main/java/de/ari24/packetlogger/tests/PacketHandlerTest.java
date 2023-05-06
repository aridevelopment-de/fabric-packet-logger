package de.ari24.packetlogger.tests;

import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.packets.PacketHandler;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.NetworkState;
import net.minecraft.network.packet.Packet;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class PacketHandlerTest {

    static final String PROGRESS_BAR_URL = "![<percentage>%](https://progress-bar.dev/<percentage>?title=All%20Play%20Packets%20(<num>/<all>))";

    @Test
    void testClientboundPlayPackets() {
        testPlayPackets(NetworkSide.CLIENTBOUND);
    }

    @Test
    void testServerboundPlayPackets() {
        testPlayPackets(NetworkSide.SERVERBOUND);
    }

    @Test
    void testClientboundLoginPackets() {
        testLoginPackets(NetworkSide.CLIENTBOUND);
    }

    @Test
    void testServerboundLoginPackets() {
        testLoginPackets(NetworkSide.SERVERBOUND);
    }

    @Test
    void testClientboundStatusPackets() {
        testStatusPackets(NetworkSide.CLIENTBOUND);
    }

    @Test
    void testServerboundStatusPackets() {
        testStatusPackets(NetworkSide.SERVERBOUND);
    }

    @Test
    void testClientboundHandshakingPackets() {
        testHandshakingPackets(NetworkSide.CLIENTBOUND);
    }

    @Test
    void testServerboundHandshakingPackets() {
        testHandshakingPackets(NetworkSide.SERVERBOUND);
    }

    void testPlayPackets(NetworkSide side) {
        Map<Class<? extends Packet<?>>, BasePacketHandler<?>> handlers = PacketHandler.getHANDLERS();
        List<Class<? extends Packet<?>>> implementedPackets = handlers.keySet().stream().toList();

        Int2ObjectMap<Class<? extends Packet<?>>> registeredPlayPacketsMapping = NetworkState.PLAY.getPacketIdToPacketMap(side);
        // Pro solution
        List<Class<? extends Packet<?>>> registeredPlayPackets = registeredPlayPacketsMapping.values().stream().filter(p -> !p.getName().contains("BundleSplitterPacket")).toList();
        compareAndPrintResults(registeredPlayPackets, implementedPackets);
    }

    void testLoginPackets(NetworkSide side) {
        Map<Class<? extends Packet<?>>, BasePacketHandler<?>> handlers = PacketHandler.getHANDLERS();
        List<Class<? extends Packet<?>>> implementedPackets = handlers.keySet().stream().toList();

        Int2ObjectMap<Class<? extends Packet<?>>> registeredPlayPacketsMapping = NetworkState.LOGIN.getPacketIdToPacketMap(side);
        List<Class<? extends Packet<?>>> registeredPlayPackets = registeredPlayPacketsMapping.values().stream().toList();
        compareAndPrintResults(registeredPlayPackets, implementedPackets);
    }

    void testStatusPackets(NetworkSide side) {
        Map<Class<? extends Packet<?>>, BasePacketHandler<?>> handlers = PacketHandler.getHANDLERS();
        List<Class<? extends Packet<?>>> implementedPackets = handlers.keySet().stream().toList();

        Int2ObjectMap<Class<? extends Packet<?>>> registeredPlayPacketsMapping = NetworkState.STATUS.getPacketIdToPacketMap(side);
        List<Class<? extends Packet<?>>> registeredPlayPackets = registeredPlayPacketsMapping.values().stream().toList();
        compareAndPrintResults(registeredPlayPackets, implementedPackets);
    }

    void testHandshakingPackets(NetworkSide side) {
        Map<Class<? extends Packet<?>>, BasePacketHandler<?>> handlers = PacketHandler.getHANDLERS();
        List<Class<? extends Packet<?>>> implementedPackets = handlers.keySet().stream().toList();

        Int2ObjectMap<Class<? extends Packet<?>>> registeredPlayPacketsMapping = NetworkState.HANDSHAKING.getPacketIdToPacketMap(side);
        List<Class<? extends Packet<?>>> registeredPlayPackets = registeredPlayPacketsMapping.values().stream().toList();
        compareAndPrintResults(registeredPlayPackets, implementedPackets);
    }

    void compareAndPrintResults(List<Class<? extends Packet<?>>> registeredPackets, List<Class<? extends Packet<?>>> implementedPackets) {
        // now compare the two lists
        List<Class<? extends Packet<?>>> missing = registeredPackets.stream().filter(p -> !implementedPackets.contains(p)).toList();
        int actuallyImplemented = registeredPackets.size() - missing.size();
        int percentageImplemented = (int) ((double) actuallyImplemented / (double) registeredPackets.size() * 100);
        int allPackets = registeredPackets.size();

        System.out.println("Sum of all packets: " + registeredPackets.size());
        System.out.println("Badge url: " + PROGRESS_BAR_URL.replaceAll("<percentage>", String.valueOf(percentageImplemented)).replaceAll("<num>", String.valueOf(actuallyImplemented)).replaceAll("<all>", String.valueOf(allPackets)));
        System.out.println(" ");

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