package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.network.packet.s2c.play.EntityAttributesS2CPacket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EntityAttributesS2CPacketHandler implements BasePacketHandler<EntityAttributesS2CPacket> {

    private JsonElement serializeAttributes(List<EntityAttributesS2CPacket.Entry> entries) {
        ArrayList<JsonObject> js = new ArrayList<>();
        for (EntityAttributesS2CPacket.Entry entry : entries) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("key", entry.getId().getTranslationKey());
            jsonObject.addProperty("value", entry.getBaseValue());

            Collection<EntityAttributeModifier> modifiers = entry.getModifiers();

            if (modifiers != null) {
                ArrayList<JsonObject> modifiersJson = new ArrayList<>();
                for (EntityAttributeModifier modifier : modifiers) {
                    JsonObject modifierJson = new JsonObject();
                    modifierJson.addProperty("uuid", modifier.getName());
                    modifierJson.addProperty("amount", modifier.getValue());
                    modifierJson.addProperty("operation", modifier.getOperation().toString());
                    modifiersJson.add(modifierJson);
                }
                jsonObject.add("modifiers", ConvertUtils.GSON_INSTANCE.toJsonTree(modifiersJson));
            }

            js.add(jsonObject);
        }
        return ConvertUtils.GSON_INSTANCE.toJsonTree(js);
    }

    @Override
    public JsonObject serialize(EntityAttributesS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();

        ConvertUtils.appendEntity(jsonObject, packet.getEntityId());
        jsonObject.add("attributes", serializeAttributes(packet.getEntries()));
        return jsonObject;
    }
}
