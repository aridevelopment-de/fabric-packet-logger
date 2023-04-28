package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.SetCameraEntityS2CPacketAccessor;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.SetCameraEntityS2CPacket;

public class SetCameraEntityS2CPacketHandler implements BasePacketHandler<SetCameraEntityS2CPacket> {


    @Override
    public JsonObject serialize(SetCameraEntityS2CPacket packet) {
        SetCameraEntityS2CPacketAccessor accessor = (SetCameraEntityS2CPacketAccessor) packet;
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, accessor.getEntityId());
        return jsonObject;
    }
}
