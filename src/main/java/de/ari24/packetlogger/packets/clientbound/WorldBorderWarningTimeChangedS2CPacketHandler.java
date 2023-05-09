package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.WorldBorderWarningTimeChangedS2CPacket;

public class WorldBorderWarningTimeChangedS2CPacketHandler implements BasePacketHandler<WorldBorderWarningTimeChangedS2CPacket> {


    @Override
    public JsonObject serialize(WorldBorderWarningTimeChangedS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("warningTime", packet.getWarningTime());
        return jsonObject;
    }
}
