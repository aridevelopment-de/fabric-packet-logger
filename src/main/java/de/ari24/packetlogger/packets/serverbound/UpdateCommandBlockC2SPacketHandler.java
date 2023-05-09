package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.block.entity.CommandBlockBlockEntity;
import net.minecraft.network.packet.c2s.play.UpdateCommandBlockC2SPacket;

import java.util.Map;

public class UpdateCommandBlockC2SPacketHandler implements BasePacketHandler<UpdateCommandBlockC2SPacket> {
    private final Map<CommandBlockBlockEntity.Type, String> TYPE_MAP = Map.of(
            CommandBlockBlockEntity.Type.SEQUENCE, "SEQUENCE",
            CommandBlockBlockEntity.Type.AUTO, "AUTO",
            CommandBlockBlockEntity.Type.REDSTONE, "REDSTONE"
    );

    @Override
    public JsonObject serialize(UpdateCommandBlockC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("pos", packet.getBlockPos().toShortString());
        jsonObject.addProperty("command", packet.getCommand());
        jsonObject.addProperty("trackOutput", packet.shouldTrackOutput());
        jsonObject.addProperty("conditional", packet.isConditional());
        jsonObject.addProperty("alwaysActive", packet.isAlwaysActive());
        jsonObject.addProperty("type", TYPE_MAP.get(packet.getType()));
        return jsonObject;
    }
}
