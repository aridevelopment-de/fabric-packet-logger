package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.MapStateUpdateDataAccessor;
import de.ari24.packetlogger.mixin.MapUpdateS2CPacketAccessor;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.block.MapColor;
import net.minecraft.item.map.MapIcon;
import net.minecraft.item.map.MapState;
import net.minecraft.network.packet.s2c.play.MapUpdateS2CPacket;

import java.util.ArrayList;
import java.util.List;

public class MapUpdateS2CPacketHandler implements BasePacketHandler<MapUpdateS2CPacket> {


    private List<JsonObject> serializeMapIcons(List<MapIcon> mapIcons) {
        List<JsonObject> jsonObjects = new ArrayList<>();

        if (mapIcons == null) {
            return jsonObjects;
        }

        mapIcons.forEach(icon -> {
            JsonObject child = new JsonObject();
            child.addProperty("type", icon.getType().toString());
            child.addProperty("x", icon.getX());
            child.addProperty("z", icon.getZ());
            child.addProperty("rotation", icon.getRotation());
            child.addProperty("hasDisplayName", icon.getText() != null);

            if (icon.getText() != null) {
                child.addProperty("displayName", icon.getText().toString());
            }

            jsonObjects.add(child);
        });

        return jsonObjects;
    }

    private JsonObject serializeUpdateData(MapState.UpdateData updateData) {
        MapStateUpdateDataAccessor accessor = (MapStateUpdateDataAccessor) updateData;
        JsonObject jsonObject = new JsonObject();

        if (updateData == null) {
            return jsonObject;
        }

        jsonObject.addProperty("x", accessor.getStartX());
        jsonObject.addProperty("z", accessor.getStartZ());
        jsonObject.addProperty("columns", accessor.getWidth());
        jsonObject.addProperty("rows", accessor.getHeight());

        List<Integer> renderedColors = new ArrayList<>();

        for (int i = 0; i < accessor.getColors().length; i++) {
            renderedColors.add(MapColor.getRenderColor(accessor.getColors()[i]));
        }

        jsonObject.add("data", ConvertUtils.GSON_INSTANCE.toJsonTree(renderedColors));
        return jsonObject;
    }

    @Override
    public JsonObject serialize(MapUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mapId", packet.getId());
        jsonObject.addProperty("scale", packet.getScale());
        jsonObject.addProperty("isLocked", packet.isLocked());

        MapUpdateS2CPacketAccessor accessor = (MapUpdateS2CPacketAccessor) packet;
        jsonObject.add("icons", ConvertUtils.GSON_INSTANCE.toJsonTree(serializeMapIcons(accessor.getIcons())));
        jsonObject.add("updateData", ConvertUtils.GSON_INSTANCE.toJsonTree(serializeUpdateData(accessor.getUpdateData())));
        return jsonObject;
    }
}
