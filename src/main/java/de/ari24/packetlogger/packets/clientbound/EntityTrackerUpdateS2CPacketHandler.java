package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.network.packet.s2c.play.EntityTrackerUpdateS2CPacket;

public class EntityTrackerUpdateS2CPacketHandler implements BasePacketHandler<EntityTrackerUpdateS2CPacket> {
    @Override
    public JsonObject serialize(EntityTrackerUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.id());

        JsonArray list = new JsonArray(packet.trackedValues().size());
        for (DataTracker.SerializedEntry<?> entry : packet.trackedValues()) {
            JsonObject jsonObject1 = new JsonObject();
            jsonObject1.addProperty("id", entry.id());
            jsonObject1.addProperty("value", entry.value().toString());
            list.add(jsonObject1);
        }

        jsonObject.add("trackedValues", ConvertUtils.GSON_INSTANCE.toJsonTree(list));
        return jsonObject;
    }
}
