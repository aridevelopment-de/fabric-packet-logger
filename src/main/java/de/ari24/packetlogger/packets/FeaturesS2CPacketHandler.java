package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.FeaturesS2CPacket;

public class FeaturesS2CPacketHandler implements BasePacketHandler<FeaturesS2CPacket> {
    @Override
    public String name() {
        return "FeatureFlags";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Feature_Flags";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Used to enable and disable features, generally experimental ones, on the client. ");
        jsonObject.addProperty("wikiVgNotes", """
                As of 1.19.4, the following feature flags are available:
                - minecraft:vanilla - enables vanilla features
                - minecraft:bundle - enables support for the bundle
                - minecraft:update_1_20 - enables all the Minecraft 1.20 features that are available in 1.19.4
                """);
        jsonObject.addProperty("features", "A list of features (identifiers) that are enabled on the client. ");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(FeaturesS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("features", ConvertUtils.GSON_INSTANCE.toJsonTree(packet.features()));
        return jsonObject;
    }
}
