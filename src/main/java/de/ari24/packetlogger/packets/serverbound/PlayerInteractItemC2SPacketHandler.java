package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;

public class PlayerInteractItemC2SPacketHandler implements BasePacketHandler<PlayerInteractItemC2SPacket> {
    @Override
    public JsonObject serialize(PlayerInteractItemC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("hand", HandSwingC2SPacketHandler.HAND_MAP.get(packet.getHand()));
        jsonObject.addProperty("sequence", packet.getSequence());
        return jsonObject;
    }
}
