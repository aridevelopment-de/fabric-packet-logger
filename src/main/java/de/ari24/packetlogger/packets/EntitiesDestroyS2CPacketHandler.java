package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.EntitiesDestroyS2CPacket;

public class EntitiesDestroyS2CPacketHandler implements BasePacketHandler<EntitiesDestroyS2CPacket> {
    @Override
    public String id() {
        return "RemoveEntities";
    }

    @Override
    public JsonObject serialize(EntitiesDestroyS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("entityIds", ConvertUtils.GSON_INSTANCE.toJsonTree(packet.getEntityIds()));
        return jsonObject;
    }
}
