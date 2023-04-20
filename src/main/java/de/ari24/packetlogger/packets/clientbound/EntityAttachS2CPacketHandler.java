package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.EntityAttachS2CPacket;

public class EntityAttachS2CPacketHandler implements BasePacketHandler<EntityAttachS2CPacket> {
    @Override
    public String name() {
        return "LinkEntities";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Link_Entities";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet is sent when an entity has been leashed to another entity.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("attached", "The entity that has been attached.");
        jsonObject.addProperty("attachedId", "The entity id of the entity that has been attached.");
        jsonObject.addProperty("holding", "The entity that holds the lead. Set to -1 to detach.");
        jsonObject.addProperty("holdingId", "The entity id of the entity that holds the lead. Set to -1 to detach.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(EntityAttachS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.getAttachedEntityId(), "attached", "attachedId");
        ConvertUtils.appendEntity(jsonObject, packet.getHoldingEntityId(), "holding", "holdingId");
        return jsonObject;
    }
}
