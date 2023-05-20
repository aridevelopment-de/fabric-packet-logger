package de.ari24.packetlogger.tests;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.packets.PacketHandler;
import de.ari24.packetlogger.packets.clientbound.BlockBreakingProgressS2CPacketHandler;
import de.ari24.packetlogger.packets.serverbound.ButtonClickC2SPacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ButtonClickC2SPacket;
import net.minecraft.network.packet.s2c.play.BlockBreakingProgressS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class DeserializeTest {
    @Test
    void testDefaultDeserialize() throws Exception {
        ButtonClickC2SPacketHandler handler = new ButtonClickC2SPacketHandler();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("windowId", 10);
        jsonObject.addProperty("buttonId", 20);
        ButtonClickC2SPacket packet = handler.deserialize(ButtonClickC2SPacket.class, jsonObject);
        System.out.println(packet);
    }

    @Test
    void testCustomDeserialize() throws Exception {
        BlockBreakingProgressS2CPacketHandler handler = new BlockBreakingProgressS2CPacketHandler();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("entityId", 10);
        jsonObject.addProperty("destroyStage", 20);
        jsonObject.addProperty("location", ConvertUtils.serializeBlockPos(new BlockPos(1, 2, 3)));

        BlockBreakingProgressS2CPacket packet = handler.deserialize(BlockBreakingProgressS2CPacket.class, jsonObject);
        System.out.println(packet);
    }

    @Test
    void getAllMissing() {
        Map<Class<? extends Packet<?>>, BasePacketHandler<?>> handlers = PacketHandler.getHANDLERS();

        for (Class<? extends Packet<?>> packetClass : handlers.keySet()) {
            BasePacketHandler<?> handler = handlers.get(packetClass);

            if (handler.getJsonParameterOrder() == null) {
                System.out.println(packetClass.getSimpleName());
            }
        }
    }
}
