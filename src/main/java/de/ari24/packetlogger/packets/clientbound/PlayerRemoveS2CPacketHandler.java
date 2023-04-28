package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.PlayerRemoveS2CPacket;

public class PlayerRemoveS2CPacketHandler implements BasePacketHandler<PlayerRemoveS2CPacket> {

    @Override
    public JsonObject serialize(PlayerRemoveS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("numPlayers", packet.profileIds().size());
        jsonObject.add("players", ConvertUtils.GSON_INSTANCE.toJsonTree(packet.profileIds()));
        return jsonObject;
    }
}
