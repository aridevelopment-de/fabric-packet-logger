package de.ari24.packetlogger.serializer;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import com.mojang.serialization.Codec;
import de.ari24.packetlogger.serializer.serializers.BaseSerializer;
import de.ari24.packetlogger.utils.ConvertUtils;
import de.ari24.packetlogger.utils.JsonObjectBuilder;
import net.minecraft.core.GlobalPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import org.apache.commons.lang3.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;

public class PacketSerializer {

    private final static Map<Class<?>, BaseSerializer> SERIALIZERS = new HashMap<>();

    // ONLY PROOF OF CONCEPT
    static {
        SERIALIZERS.put(Optional.class, (jsonObject, key, object, depth) -> {
            Optional<?> o = (Optional<?>) object;

            if (o.isPresent()) {
                jsonObject.add(key, serializeObject(o.get(), depth + 1));
            } else {
                jsonObject.add(key, null);
            }
        });
        SERIALIZERS.put(Component.class, (jsonObject, key, object, depth) -> {
            Component component = (Component) object;
            jsonObject.addProperty(key, component.getString());
        });
        SERIALIZERS.put(ResourceLocation.class, (jsonObject, key, object, depth) -> {
            ResourceLocation resourceLocation = (ResourceLocation) object;
            jsonObject.addProperty(key, resourceLocation.toString());
        });
        SERIALIZERS.put(UUID.class, (jsonObject, key, object, depth) -> {
            UUID uuid = (UUID) object;
            jsonObject.addProperty(key, uuid.toString());
        });
        SERIALIZERS.put(List.class, (jsonObject, key, object, depth) -> {
            List<?> list = (List<?>) object;

            for (Object o : list) {
                jsonObject.add(key, serializeObject(o, depth + 1));
            }
        });
        SERIALIZERS.put(Map.class, (jsonObject, key, object, depth) -> {
            Map<?, ?> map = (Map<?, ?>) object;

            for (Map.Entry<?, ?> entry : map.entrySet()) {
                jsonObject.add(key, serializeObject(entry, depth + 1));
            }
        });
        SERIALIZERS.put(Set.class, (jsonObject, key, object, depth) -> {
            Set<?> set = (Set<?>) object;

            for (Object o : set) {
                jsonObject.add(key, serializeObject(o, depth + 1));
            }
        });
        SERIALIZERS.put(Function.class, (jsonObject, key, object, depth) -> {
            Function<?, ?> function = (Function<?, ?>) object;
            jsonObject.addProperty(key, function.toString());
        });
        SERIALIZERS.put(Codec.class, (jsonObject, key, object, depth) -> {});
        SERIALIZERS.put(GameProfile.class, (jsonObject, key, object, depth) -> {
            GameProfile gameProfile = (GameProfile) object;
            jsonObject.add(key, new JsonObjectBuilder()
                    .add("name", gameProfile.getName())
                    .add("uuid", gameProfile.getId().toString())
                    .add("isLegacy", gameProfile.isLegacy())
                    .add("isComplete", gameProfile.isComplete()).build());
        });
        SERIALIZERS.put(RegistryOps.class, (jsonObject, key, object, depth) -> {});
        SERIALIZERS.put(ResourceKey.class, (jsonObject, key, object, depth) -> {
            ResourceKey<?> resourceKey = (ResourceKey<?>) object;
            jsonObject.addProperty(key, resourceKey.location().toString());
        });
        SERIALIZERS.put(Level.class, ((jsonObject, key, object, depth) -> {
            Level level = (Level) object;
            jsonObject.addProperty(key, level.dimension().location().toString());
        }));
        SERIALIZERS.put(DimensionType.class, (jsonObject, key, object, depth) -> {
            DimensionType dimensionType = (DimensionType) object;
            jsonObject.addProperty(key, "TBD");
        });
        SERIALIZERS.put(GlobalPos.class, (jsonObject, key, object, depth) -> {
            GlobalPos globalPos = (GlobalPos) object;
            jsonObject.addProperty(key, globalPos.toString());
        });
        SERIALIZERS.put(Annotation.class, (jsonObject, key, object, depth) -> {});
        SERIALIZERS.put(EntityType.class, (jsonObject, key, object, depth) -> {
            EntityType<?> entityType = (EntityType<?>) object;
            jsonObject.addProperty(key, entityType.toShortString());
        });
    }

    public static JsonObject serializePacket(Packet<?> packet) {
        try {
            return serializeObject(packet, 0);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static JsonObject serializeObject(Object object, int depth) throws IllegalAccessException {
        if (object == null) {
            return null;
        }

        if (depth > 2) {
            return null;
        }

        JsonObject jsonObject = new JsonObject();
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(object);

            if (value == null) {
                jsonObject.add(field.getName(), null);
            } else if (ClassUtils.isPrimitiveOrWrapper(value.getClass())) {
                jsonObject.addProperty(field.getName(), value.toString());
            } else if (value instanceof String) {
                jsonObject.addProperty(field.getName(), (String) value);
            } else if (value instanceof Enum<?> e) {
                jsonObject.addProperty(field.getName(), e.name());
            } else if (SERIALIZERS.keySet().stream().anyMatch(c -> c.isAssignableFrom(value.getClass()))) {
                SERIALIZERS.keySet().stream()
                        .filter(c -> c.isAssignableFrom(value.getClass()))
                        .findFirst()
                        .ifPresent(c -> {
                            try {
                                SERIALIZERS.get(c).serialize(jsonObject, field.getName(), value, depth);
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        });
            } else {
                jsonObject.add(field.getName(), serializeObject(value, depth + 1));
            }
        }

        return jsonObject;
    }
}
