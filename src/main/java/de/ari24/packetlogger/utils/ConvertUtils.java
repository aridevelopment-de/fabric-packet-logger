package de.ari24.packetlogger.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import de.ari24.packetlogger.PacketLogger;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.ChunkData;
import net.minecraft.network.packet.s2c.play.LightData;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.GlobalPos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConvertUtils {
    public static final Gson GSON_INSTANCE = new Gson();

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
        ClientWorld clientWorld = MinecraftClient.getInstance().world;

        if (PacketLogger.CONFIG.resolveEntityIdsToEntities() && clientWorld != null) {
            Entity entity = clientWorld.getEntityById(entityId);

            if (entity != null) {
                jsonObject.add("entity", ConvertUtils.serializeEntity(entity));
            } else {
                jsonObject.addProperty("entityId", entityId);
            }
        } else {
            jsonObject.addProperty("entityId", entityId);
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
}
