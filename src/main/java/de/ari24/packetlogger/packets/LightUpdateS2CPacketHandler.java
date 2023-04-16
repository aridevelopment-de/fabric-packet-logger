package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.LightUpdateS2CPacket;

public class LightUpdateS2CPacketHandler implements BasePacketHandler<LightUpdateS2CPacket> {
    @Override
    public String name() {
        return "UpdateLight";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Update_Light";
    }

    @Override
    public JsonObject serialize(LightUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("chunkX", packet.getChunkX());
        jsonObject.addProperty("chunkZ", packet.getChunkZ());
        jsonObject.add("lightData", ConvertUtils.serializeLightData(packet.getData()));
        return jsonObject;
    }
}

