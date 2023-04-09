package de.ari24.packetlogger.serializer.serializers;

import com.google.gson.JsonObject;

public interface BaseSerializer {
    void serialize(JsonObject jsonObject, String key, Object object, int depth) throws IllegalAccessException;
}
