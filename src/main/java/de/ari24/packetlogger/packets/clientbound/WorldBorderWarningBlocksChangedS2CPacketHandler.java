package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.WorldBorderWarningBlocksChangedS2CPacket;

public class WorldBorderWarningBlocksChangedS2CPacketHandler implements BasePacketHandler<WorldBorderWarningBlocksChangedS2CPacket> {

    @Override
    public JsonObject serialize(WorldBorderWarningBlocksChangedS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("warningBlocks", packet.getWarningBlocks());
        return jsonObject;
    }
}
