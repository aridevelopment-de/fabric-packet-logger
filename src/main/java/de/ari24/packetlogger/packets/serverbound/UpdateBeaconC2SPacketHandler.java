package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.c2s.play.UpdateBeaconC2SPacket;

public class UpdateBeaconC2SPacketHandler implements BasePacketHandler<UpdateBeaconC2SPacket> {
    @Override
    public JsonObject serialize(UpdateBeaconC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();

        if (packet.getPrimaryEffectId().isPresent()) {
            jsonObject.add("primaryEffect", ConvertUtils.serializeStatusEffect(packet.getPrimaryEffectId().get()));
        } else {
            jsonObject.add("primaryEffect", JsonNull.INSTANCE);
        }

        if (packet.getSecondaryEffectId().isPresent()) {
            jsonObject.add("secondaryEffect", ConvertUtils.serializeStatusEffect(packet.getSecondaryEffectId().get()));
        } else {
            jsonObject.add("secondaryEffect", JsonNull.INSTANCE);
        }

        return jsonObject;
    }
}
