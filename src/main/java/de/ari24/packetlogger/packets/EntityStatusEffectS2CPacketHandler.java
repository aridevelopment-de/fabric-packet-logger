package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.EntityStatusEffectS2CPacket;

public class EntityStatusEffectS2CPacketHandler implements BasePacketHandler<EntityStatusEffectS2CPacket> {
    @Override
    public String name() {
        return "EntityEffect";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Entity_Effect";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet is being used for adding / updating potion effects.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("entityId", "The entity id of the entity that has the effect.");
        jsonObject.addProperty("effect", "Details of the potion effect being added.");
        jsonObject.addProperty("amplifier", "The amplifier of the potion effect, starting at 0. Notchian client displays effect level as amplifier + 1");
        jsonObject.addProperty("duration", "The effect duration in ticks. -1 means infinite");
        jsonObject.addProperty("showParticles", "Effects with particles hidden are not included in the calculation of the effect color, and are not rendered on the HUD (but are still rendered within the inventory).");
        jsonObject.addProperty("isAmbient", "Set to true if the effect was applied from a beacon. All beacon-generated effects are ambient. Ambient effects use a different icon in the HUD (blue border rather than gray). If all effects on an entity are ambient, the \"Is potion effect ambient\" living metadata field should be set to true.");
        jsonObject.addProperty("showIcon", "Whether to display an icon in the client hud (Still visible in the inventory)");
        jsonObject.addProperty("hasFactorData", "Used in Darkness effect");
        jsonObject.addProperty("factorCoded", "TODO");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(EntityStatusEffectS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.getEntityId());
        jsonObject.add("effect", ConvertUtils.serializeStatusEffect(packet.getEffectId()));
        jsonObject.addProperty("amplifier", packet.getAmplifier());
        jsonObject.addProperty("duration", packet.getDuration());
        jsonObject.addProperty("showParticles", packet.shouldShowParticles());
        jsonObject.addProperty("isAmbient", packet.isAmbient());
        jsonObject.addProperty("showIcon", packet.shouldShowIcon());
        jsonObject.addProperty("hasFactorData", packet.getFactorCalculationData() != null);
        jsonObject.addProperty("factorCoded", "TODO");
        return jsonObject;
    }
}
