package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;

public class PlayerInteractBlockC2SPacketHandler implements BasePacketHandler<PlayerInteractBlockC2SPacket> {
    @Override
    public JsonObject serialize(PlayerInteractBlockC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("hand", HandSwingC2SPacketHandler.HAND_MAP.get(packet.getHand()));
        jsonObject.addProperty("location", packet.getBlockHitResult().getBlockPos().toShortString());
        jsonObject.addProperty("cursorPosition", packet.getBlockHitResult().getPos().toString());
        jsonObject.addProperty("insideBlock", packet.getBlockHitResult().isInsideBlock());
        jsonObject.addProperty("sequence", packet.getSequence());
        return jsonObject;
    }
}
