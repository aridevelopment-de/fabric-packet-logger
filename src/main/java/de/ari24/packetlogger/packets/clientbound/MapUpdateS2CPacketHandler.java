package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonNull;
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
    @Override
    public String name() {
        return "MapData";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Map_Data";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Updates a rectangular area on a map item. See https://minecraft.fandom.com/wiki/Map for more information.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("mapId", "Map ID of the map being modified. Should be unique.");
        jsonObject.addProperty("scale", "From 0 for a fully zoomed-in map (1 block per pixel) to 4 for a fully zoomed-out map (16 blocks per pixel)");
        jsonObject.addProperty("isLocked", "True if the map has been locked in a cartography table");
        jsonObject.addProperty("icons.type", "Types are based off of rows and columns in map_icons.png");
        jsonObject.addProperty("icons.x", "Map coordinates: -128 for furthest left, 127 for furthest right");
        jsonObject.addProperty("icons.z", "Map coordinates: -128 for highest, 127 for lowest");
        jsonObject.addProperty("icons.rotation", "0-15. A direction of 0 is a vertical icon and increments by 22.5 degrees clockwise (360/16)");
        jsonObject.addProperty("icons.hasDisplayName", "If the icon should have a display name");
        jsonObject.addProperty("icons.displayName", "The display name of the icon");
        jsonObject.addProperty("updateData.x", "Only if columns is more than 0: x offset of the westernmost column");
        jsonObject.addProperty("updateData.z", "Only if rows is more than 0: z offset of the northernmost row");
        jsonObject.addProperty("updateData.columns", "Number of columns updated");
        jsonObject.addProperty("updateData.rows", "Only if columns is more than 0: Number of rows updated");
        jsonObject.addProperty("updateData.data", "Only if columns is more than 0. Single dimension array with rows*columns size. Already converted to ABGR integer format.");
        return jsonObject;
    }

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
