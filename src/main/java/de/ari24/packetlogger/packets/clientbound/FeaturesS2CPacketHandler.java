package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.FeaturesS2CPacket;

public class FeaturesS2CPacketHandler implements BasePacketHandler<FeaturesS2CPacket> {


    @Override
    public JsonObject serialize(FeaturesS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("features", ConvertUtils.GSON_INSTANCE.toJsonTree(packet.features()));
        return jsonObject;
    }
}
