package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;

import java.util.Map;

public class PlayerActionC2SPacketHandler implements BasePacketHandler<PlayerActionC2SPacket> {
    private final Map<PlayerActionC2SPacket.Action, String> ACTION_MAP = Map.of(
            PlayerActionC2SPacket.Action.START_DESTROY_BLOCK, "StartDestroyBlock",
            PlayerActionC2SPacket.Action.ABORT_DESTROY_BLOCK, "AbortDestroyBlock",
            PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK, "StopDestroyBlock",
            PlayerActionC2SPacket.Action.DROP_ALL_ITEMS, "DropAllItems",
            PlayerActionC2SPacket.Action.DROP_ITEM, "DropItem",
            PlayerActionC2SPacket.Action.RELEASE_USE_ITEM, "ReleaseUseItem",
            PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND, "SwapItemWithOffhand"
    );

    @Override
    public JsonObject serialize(PlayerActionC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("pos", packet.getPos().toShortString());
        jsonObject.addProperty("direction", ConvertUtils.DIRECTION_MAP.get(packet.getDirection()));
        jsonObject.addProperty("action", packet.getAction().ordinal());
        jsonObject.addProperty("actionName", ACTION_MAP.get(packet.getAction()));
        jsonObject.addProperty("sequence", packet.getSequence());
        return jsonObject;
    }
}
