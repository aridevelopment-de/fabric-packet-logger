package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.EntityAnimationS2CPacket;

import java.util.List;

public class EntityAnimationS2CPacketHandler implements BasePacketHandler<EntityAnimationS2CPacket> {
    static final List<String> ANIMATION_IDS = List.of(
            "SWING_MAIN_HAND",
            "TAKE_DAMAGE",
            "WAKE_UP",
            "SWING_OFFHAND",
            "CRITICAL_EFFECT",
            "MAGIC_CRITICAL_EFFECT"
    );


    @Override
    public JsonObject serialize(EntityAnimationS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.getId());

        if (packet.getAnimationId() < ANIMATION_IDS.size() && packet.getAnimationId() >= 0) {
            jsonObject.addProperty("animation", ANIMATION_IDS.get(packet.getAnimationId()));
        } else {
            jsonObject.addProperty("animationId", packet.getAnimationId());
        }

        return jsonObject;
    }
}
