package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.FeaturesS2CPacket;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;

public class FeaturesS2CPacketHandler implements BasePacketHandler<FeaturesS2CPacket> {

    @Override
    public FeaturesS2CPacket deserialize(Class<FeaturesS2CPacket> clazz, JsonObject json) throws Exception {
        Set<Identifier> features = new HashSet<>();

        for (JsonElement featureElement : json.getAsJsonArray("features")) {
            Identifier feature = Identifier.tryParse(featureElement.getAsString());

            if (feature == null) {
                throw new Exception("Failed to parse feature: " + featureElement.getAsString());
            } else {
                features.add(feature);
            }
        }

        return new FeaturesS2CPacket(features);
    }

    @Override
    public JsonObject serialize(FeaturesS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("features", ConvertUtils.GSON_INSTANCE.toJsonTree(packet.features()));
        return jsonObject;
    }
}
