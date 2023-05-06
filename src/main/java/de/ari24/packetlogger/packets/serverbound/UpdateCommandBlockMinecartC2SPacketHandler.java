package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.UpdateCommandBlockMinecartC2SPacketAccessor;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.c2s.play.UpdateCommandBlockMinecartC2SPacket;

public class UpdateCommandBlockMinecartC2SPacketHandler implements BasePacketHandler<UpdateCommandBlockMinecartC2SPacket> {
    @Override
    public JsonObject serialize(UpdateCommandBlockMinecartC2SPacket packet) {
        UpdateCommandBlockMinecartC2SPacketAccessor accessor = (UpdateCommandBlockMinecartC2SPacketAccessor) packet;

        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, accessor.getEntityId());
        jsonObject.addProperty("command", packet.getCommand());
        jsonObject.addProperty("trackOutput", packet.shouldTrackOutput());
        return jsonObject;
    }
}
