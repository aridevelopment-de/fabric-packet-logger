package de.ari24.packetlogger.utils;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

public class JsonObjectBuilder {
    private final JsonObject jsonObject;

    public JsonObjectBuilder() {
        this.jsonObject = new JsonObject();
    }

    public JsonObjectBuilder add(String key, Object value) {
        if (value == null) {
            jsonObject.add(key, JsonNull.INSTANCE);
        } else if (value instanceof Number) {
            jsonObject.addProperty(key, (Number) value);
        } else if (value instanceof Boolean) {
            jsonObject.addProperty(key, (Boolean) value);
        } else if (value instanceof Character) {
            jsonObject.addProperty(key, (Character) value);
        } else if (value instanceof String) {
            jsonObject.addProperty(key, (String) value);
        } else {
            jsonObject.add(key, ConvertUtils.GSON_INSTANCE.toJsonTree(value));
        }
        return this;
    }

    public JsonObject build() {
        return jsonObject;
    }
}
