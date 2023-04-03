package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;

public class HandSwingS2CPacketHandler implements BasePacketHandler<HandSwingC2SPacket> {
    @Override
    public String id() {
        return "SwingArm";
    }

    @Override
    public JsonObject serialize(HandSwingC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("hand", packet.getHand().name());
        return jsonObject;
    }
}
