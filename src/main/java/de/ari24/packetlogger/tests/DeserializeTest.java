package de.ari24.packetlogger.tests;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.serverbound.ButtonClickC2SPacketHandler;
import net.minecraft.network.packet.c2s.play.ButtonClickC2SPacket;
import org.junit.jupiter.api.Test;

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
}
