package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.block.entity.StructureBlockBlockEntity;
import net.minecraft.network.packet.c2s.play.UpdateStructureBlockC2SPacket;

import java.util.Map;

public class UpdateStructureBlockC2SPacketHandler implements BasePacketHandler<UpdateStructureBlockC2SPacket> {
    private final Map<StructureBlockBlockEntity.Action, String> ACTION_MAP = Map.of(
            StructureBlockBlockEntity.Action.UPDATE_DATA, "UPDATE_DATA",
            StructureBlockBlockEntity.Action.SAVE_AREA, "SAVE_AREA",
            StructureBlockBlockEntity.Action.LOAD_AREA, "LOAD_AREA",
            StructureBlockBlockEntity.Action.SCAN_AREA, "SCAN_AREA"
    );

    @Override
    public JsonObject serialize(UpdateStructureBlockC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("location", packet.getPos().toShortString());
        jsonObject.addProperty("action", ACTION_MAP.get(packet.getAction()));
        jsonObject.addProperty("mode", packet.getMode().asString());
        jsonObject.addProperty("name", packet.getTemplateName());
        jsonObject.addProperty("offset", packet.getOffset().toShortString());
        jsonObject.addProperty("size", packet.getSize().toShortString());
        jsonObject.addProperty("mirror", packet.getMirror().asString());
        jsonObject.addProperty("rotation", packet.getRotation().asString());
        jsonObject.addProperty("metadata", packet.getMetadata());
        jsonObject.addProperty("integrity", packet.getIntegrity());
        jsonObject.addProperty("seed", packet.getSeed());
        jsonObject.addProperty("ignoreEntities", packet.shouldIgnoreEntities());
        jsonObject.addProperty("showAir", packet.shouldShowAir());
        jsonObject.addProperty("showBoundingBox", packet.shouldShowBoundingBox());
        return jsonObject;
    }
}
