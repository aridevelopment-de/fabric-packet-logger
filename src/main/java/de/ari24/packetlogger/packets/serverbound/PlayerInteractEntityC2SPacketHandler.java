package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.PlayerInteractEntityC2SPacketAccessor;
import de.ari24.packetlogger.mixin.PlayerInteractEntityC2SPacketInteractAtHandlerAccessor;
import de.ari24.packetlogger.mixin.PlayerInteractEntityC2SPacketInteractHandlerAccessor;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;

import java.util.Map;

public class PlayerInteractEntityC2SPacketHandler implements BasePacketHandler<PlayerInteractEntityC2SPacket> {
    private final Map<PlayerInteractEntityC2SPacket.InteractType, String> INTERACT_TYPE_MAP = Map.of(
            PlayerInteractEntityC2SPacket.InteractType.INTERACT, "INTERACT",
            PlayerInteractEntityC2SPacket.InteractType.ATTACK, "ATTACK",
            PlayerInteractEntityC2SPacket.InteractType.INTERACT_AT, "INTERACT_AT"
    );

    @Override
    public JsonObject serialize(PlayerInteractEntityC2SPacket packet) {
        PlayerInteractEntityC2SPacketAccessor accessor = (PlayerInteractEntityC2SPacketAccessor) packet;

        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, accessor.getEntityId());
        jsonObject.addProperty("type", INTERACT_TYPE_MAP.get(accessor.getType().getType()));

        if (accessor.getType() instanceof PlayerInteractEntityC2SPacket.InteractAtHandler interactAtHandler) {
            PlayerInteractEntityC2SPacketInteractAtHandlerAccessor interactAtAccessor = (PlayerInteractEntityC2SPacketInteractAtHandlerAccessor) interactAtHandler;
            jsonObject.addProperty("targetX", interactAtAccessor.getPos().getX());
            jsonObject.addProperty("targetY", interactAtAccessor.getPos().getY());
            jsonObject.addProperty("targetZ", interactAtAccessor.getPos().getZ());
            jsonObject.addProperty("hand", HandSwingC2SPacketHandler.HAND_MAP.get(interactAtAccessor.getHand()));
        } else if (accessor.getType() instanceof PlayerInteractEntityC2SPacket.InteractHandler interactHandler) {
            PlayerInteractEntityC2SPacketInteractHandlerAccessor interactAccessor = (PlayerInteractEntityC2SPacketInteractHandlerAccessor) interactHandler;
            jsonObject.addProperty("hand", HandSwingC2SPacketHandler.HAND_MAP.get(interactAccessor.getHand()));
        }

        jsonObject.addProperty("sneaking", packet.isPlayerSneaking());
        return jsonObject;
    }
}
