package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.LookAtS2CPacketAccessor;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.LookAtS2CPacket;

public class LookAtS2CPacketHandler implements BasePacketHandler<LookAtS2CPacket> {

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
