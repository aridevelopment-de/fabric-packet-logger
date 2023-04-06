package de.ari24.packetlogger.packets;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.PacketLogger;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.network.packet.s2c.play.EntityAttributesS2CPacket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EntityAttributesS2CPacketHandler implements BasePacketHandler<EntityAttributesS2CPacket> {
    @Override
    public String name() {
        return "UpdateAttributes";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Update_Attributes";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();;
        jsonObject.addProperty("general", "Sets attributes on the given entity. See https://minecraft.fandom.com/wiki/Attribute for a list of attributes.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("entityId", "The entity id to set the attributes on.");
        jsonObject.addProperty("attributes.key", "The attribute key.");
        jsonObject.addProperty("attributes.value", "The attribute value.");
        jsonObject.addProperty("attributes.modifiers.uuid", "The modifier uuid.");
        jsonObject.addProperty("attributes.modifiers.amount", "May be positive or negative.");
        jsonObject.addProperty("attributes.modifiers.operation", "All of the 0's are applied first, and then the 1's, and then the 2's.");
        return jsonObject;
    }

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
