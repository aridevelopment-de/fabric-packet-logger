package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.util.Hand;

import java.util.Map;

public class HandSwingC2SPacketHandler implements BasePacketHandler<HandSwingC2SPacket> {
    public static final Map<Hand, String> HAND_MAP = Map.of(
            Hand.MAIN_HAND, "MAIN_HAND",
            Hand.OFF_HAND, "OFF_HAND"
    );

    @Override
    public JsonObject serialize(HandSwingC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("hand", HAND_MAP.get(packet.getHand()));
        return jsonObject;
    }
}
