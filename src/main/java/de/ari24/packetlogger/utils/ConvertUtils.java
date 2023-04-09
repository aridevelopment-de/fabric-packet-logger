package de.ari24.packetlogger.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;

public class ConvertUtils {
    public static final Gson GSON_INSTANCE = new Gson();

    public static void appendGameProfileDescription(JsonObject jsonObject) {
        jsonObject.addProperty("uuid", "UUID of the player");
        jsonObject.addProperty("name", "Name of the player. Length should be at 16 characters maximum");
        jsonObject.addProperty("legacy", "Whether the player is a legacy player. Most likely indicates a player joining an offline server");
        jsonObject.addProperty("properties", "Properties of the player. TODO: Add example info");
    }

    public static JsonObject serializeGameProfile(GameProfile gameProfile) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid", gameProfile.getId().toString());
        jsonObject.addProperty("name", gameProfile.getName());
        jsonObject.addProperty("legacy", gameProfile.isLegacy());
        jsonObject.add("properties", GSON_INSTANCE.toJsonTree(gameProfile.getProperties()));
        return jsonObject;
    }

    /*public static JsonObject serializeGlobalPos(GlobalPos globalPos) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("dimension", globalPos.getDimension().getValue().toString());
        jsonObject.addProperty("position", globalPos.getPos().toString());
        return jsonObject;
    }

    public static JsonObject serializeLightData(LightData data) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("isNonEdge", data.isNonEdge());
        jsonObject.addProperty("initedSky", data.getInitedSky().toString());
        jsonObject.addProperty("initedBlock", data.getInitedBlock().toString());
        jsonObject.addProperty("uninitedSky", data.getUninitedSky().toString());
        jsonObject.addProperty("uninitedBlock", data.getUninitedBlock().toString());
        jsonObject.add("skyNibbles", GSON_INSTANCE.toJsonTree(data.getSkyNibbles()));
        jsonObject.add("blockNibbles", GSON_INSTANCE.toJsonTree(data.getBlockNibbles()));
        return jsonObject;
    }

    public static JsonObject serializeChunkData(ChunkData chunkData) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("heightMap", chunkData.getHeightmap().toString());
        jsonObject.addProperty("sectionDataReadableBytes", chunkData.getSectionsDataBuf().readableBytes());
        return jsonObject;
    }

    public static JsonObject serializeItem(Item item) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", item.toString());
        jsonObject.addProperty("translationKey", item.getTranslationKey());
        jsonObject.addProperty("maxCount", item.getMaxCount());
        return jsonObject;
    }

    public static JsonObject serializeItemStack(ItemStack itemStack) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("isEmpty", itemStack.isEmpty());

        if (!itemStack.isEmpty()) {
            Item item = itemStack.getItem();
            jsonObject.addProperty("type", item.toString());
            jsonObject.addProperty("count", itemStack.getCount());

            if ((item.isDamageable() || item.isNbtSynced()) && itemStack.getNbt() != null) {
                jsonObject.addProperty("nbt", itemStack.getNbt().asString());
            }
        }

        return jsonObject;
    }

    public static void appendEntity(JsonObject jsonObject, int entityId) {
        appendEntity(jsonObject, entityId, "entity", "entityId");
    }

    public static void appendEntity(JsonObject jsonObject, int entityId, String key, String idKey) {
        ClientWorld clientWorld = MinecraftClient.getInstance().world;

        if (PacketLogger.CONFIG.resolveEntityIdsToEntities() && clientWorld != null) {
            Entity entity = clientWorld.getEntityById(entityId);

            if (entity != null) {
                jsonObject.add(key, ConvertUtils.serializeEntity(entity));
            } else {
                jsonObject.addProperty(idKey, entityId);
            }
        } else {
            jsonObject.addProperty(idKey, entityId);
        }
    }

    public static List<JsonObject> serializeEntities(List<Entity> entities) {
        List<JsonObject> jsonObjects = new ArrayList<>();
        for (Entity entity : entities) {
            jsonObjects.add(serializeEntity(entity));
        }
        return jsonObjects;
    }

    public static JsonObject serializeEntity(Entity entity) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("entityId", entity.getId());
        jsonObject.addProperty("type", entity.getType().toString());
        jsonObject.addProperty("uuid", entity.getUuidAsString());
        jsonObject.addProperty("name", entity.getDisplayName().toString());
        jsonObject.addProperty("world", entity.getWorld().toString());
        jsonObject.addProperty("pos", entity.getPos().toString());
        return jsonObject;
    }

    public static String convertRGB(int color) {
        // to rgb
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color) & 0xFF;
        return "#" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
    }

    public static JsonObject serializeStatusEffect(StatusEffect statusEffect) {
        JsonObject jsonObject = new JsonObject();

        Identifier identifier = Registries.STATUS_EFFECT.getId(statusEffect);

        if (identifier != null) {
            jsonObject.addProperty("id", identifier.toString());
        } else {
            jsonObject.addProperty("id", "not in registry");
        }

        jsonObject.addProperty("translation", statusEffect.getTranslationKey());
        jsonObject.addProperty("category", statusEffect.getCategory().name());
        jsonObject.addProperty("color", convertRGB(statusEffect.getColor()));
        jsonObject.addProperty("positive", statusEffect.isBeneficial());
        return jsonObject;
    }*/
}
