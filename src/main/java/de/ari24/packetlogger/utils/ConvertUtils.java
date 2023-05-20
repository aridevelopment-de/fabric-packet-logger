package de.ari24.packetlogger.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.authlib.GameProfile;
import de.ari24.packetlogger.PacketLogger;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.ChunkData;
import net.minecraft.network.packet.s2c.play.LightData;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class ConvertUtils {
    public static final Gson GSON_INSTANCE = new Gson();
    public static final Map<Direction, String> DIRECTION_MAP = Map.of(
            Direction.DOWN, "down",
            Direction.UP, "up",
            Direction.NORTH, "north",
            Direction.SOUTH, "south",
            Direction.WEST, "west",
            Direction.EAST, "east"
    );

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

    public static JsonObject serializeGlobalPos(GlobalPos globalPos) {
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
        jsonObject.addProperty(idKey, entityId);

        if (PacketLogger.CONFIG.resolveEntityIdsToEntities() && clientWorld != null) {
            Entity entity = clientWorld.getEntityById(entityId);

            if (entity != null) {
                jsonObject.add(key, ConvertUtils.serializeEntity(entity));
            }
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

    public static String serializeBlockPos(BlockPos pos) {
        return pos.getX() + "," + pos.getY() + "," + pos.getZ();
    }

    public static BlockPos deserializeBlockPos(String pos) {
        String[] split = pos.split(",");
        return new BlockPos(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
    }

    public static String serializeVec3d(Vec3d vec3d) {
        return vec3d.getX() + "," + vec3d.getY() + "," + vec3d.getZ();
    }

    public static Vec3d deserializeVec3d(String vec3d) {
        String[] split = vec3d.split(",");
        return new Vec3d(Double.parseDouble(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]));
    }

    public static String convertRGB(int color) {
        // to rgb
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color) & 0xFF;
        return "#" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
    }

    public static JsonObject serializeStatusEffect(@Nullable StatusEffect statusEffect) {
        JsonObject jsonObject = new JsonObject();

        if (statusEffect == null) {
            return jsonObject;
        }

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
    }

    public static Object convertJsonPrimitive(JsonPrimitive primitive) {
        if (primitive.isBoolean()) {
            return primitive.getAsBoolean();
        } else if (primitive.isNumber()) {
            return primitive.getAsNumber();
        } else if (primitive.isString()) {
            return primitive.getAsString();
        } else if (primitive.isJsonNull()) {
            return null;
        } else if (primitive.isJsonArray()) {
            return primitive.getAsJsonArray().asList();
        } else if (primitive.isJsonObject()) {
            return primitive.getAsJsonObject();
        } else {
            return primitive;
        }
    }
}
