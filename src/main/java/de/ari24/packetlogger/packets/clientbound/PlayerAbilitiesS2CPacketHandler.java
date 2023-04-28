package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.PlayerAbilitiesS2CPacket;

public class PlayerAbilitiesS2CPacketHandler implements BasePacketHandler<PlayerAbilitiesS2CPacket> {

    @Override
    public JsonObject serialize(PlayerAbilitiesS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("invulnerable", packet.isInvulnerable());
        jsonObject.addProperty("flying", packet.isFlying());
        jsonObject.addProperty("allowFlying", packet.allowFlying());
        jsonObject.addProperty("creativeMode", packet.isCreativeMode());
        jsonObject.addProperty("flySpeed", packet.getFlySpeed());
        jsonObject.addProperty("fieldOfViewModifier", packet.getWalkSpeed());
        return jsonObject;
    }
}
