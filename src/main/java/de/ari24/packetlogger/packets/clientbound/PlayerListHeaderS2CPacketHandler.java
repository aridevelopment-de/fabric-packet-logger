package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.PlayerListHeaderS2CPacket;

public class PlayerListHeaderS2CPacketHandler implements BasePacketHandler<PlayerListHeaderS2CPacket> {


    @Override
    public JsonObject serialize(PlayerListHeaderS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("header", packet.getHeader().getString());
        jsonObject.addProperty("footer", packet.getFooter().getString());
        return jsonObject;
    }
}
