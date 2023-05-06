package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.ResourcePackStatusC2SPacket;

import java.util.Map;

public class ResourcePackStatusC2SPacketHandler implements BasePacketHandler<ResourcePackStatusC2SPacket> {
    private final Map<ResourcePackStatusC2SPacket.Status, String> STATUS_MAP = Map.of(
            ResourcePackStatusC2SPacket.Status.SUCCESSFULLY_LOADED, "SUCCESSFULLY_LOADED",
            ResourcePackStatusC2SPacket.Status.DECLINED, "DECLINED",
            ResourcePackStatusC2SPacket.Status.FAILED_DOWNLOAD, "FAILED_DOWNLOAD",
            ResourcePackStatusC2SPacket.Status.ACCEPTED, "ACCEPTED"
    );

    @Override
    public JsonObject serialize(ResourcePackStatusC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", STATUS_MAP.get(packet.getStatus()));
        return jsonObject;
    }
}
