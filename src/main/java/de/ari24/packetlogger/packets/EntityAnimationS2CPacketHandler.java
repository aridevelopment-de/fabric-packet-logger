package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
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
    public String name() {
        return "EntityAnimation";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Entity_Animation";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sent whenever an entity should change animation. ");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("entityId", "The entity ID of the entity that should change animation.");
        jsonObject.addProperty("animation", "Animation that will be played. ");
        return jsonObject;
    }

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
