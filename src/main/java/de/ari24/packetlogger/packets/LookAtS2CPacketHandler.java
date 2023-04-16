package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.LookAtS2CPacketAccessor;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.LookAtS2CPacket;

public class LookAtS2CPacketHandler implements BasePacketHandler<LookAtS2CPacket> {
    @Override
    public String name() {
        return "LookAt";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Look_At";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Used to rotate the client player to face the given location or entity (for /teleport [<targets>] <x> <y> <z> facing).");
        jsonObject.addProperty("wikiVgNotes", "If the entity given by entity ID cannot be found, this packet should be treated as if is entity was false. ");
        jsonObject.addProperty("selfAnchor", "f set to eyes, aims using the head position; otherwise aims using the feet position.");
        jsonObject.addProperty("targetX", "x coordinate of the point to face towards");
        jsonObject.addProperty("targetY", "y coordinate of the point to face towards");
        jsonObject.addProperty("targetZ", "z coordinate of the point to face towards");
        jsonObject.addProperty("isEntity", "If true, additional information about an entity is provided. Also being used when client retrieves the vec3d target position (via head/feet)");
        jsonObject.addProperty("entityId", "Entity ID of the entity to face towards");
        jsonObject.addProperty("targetAnchor", "f set to eyes, aims using the head position; otherwise aims using the feet position.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(LookAtS2CPacket packet) {
        LookAtS2CPacketAccessor accessor = (LookAtS2CPacketAccessor) packet;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("selfAnchor", packet.getSelfAnchor().name());
        jsonObject.addProperty("targetX", accessor.getTargetX());
        jsonObject.addProperty("targetY", accessor.getTargetY());
        jsonObject.addProperty("targetZ", accessor.getTargetZ());
        jsonObject.addProperty("isEntity", accessor.getLookAtEntity());

        if (accessor.getLookAtEntity()) {
            ConvertUtils.appendEntity(jsonObject, accessor.getEntityId());
            jsonObject.addProperty("targetAnchor", accessor.getTargetAnchor().name());
        }

        return jsonObject;
    }
}
