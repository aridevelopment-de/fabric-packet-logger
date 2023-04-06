package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.PlayerAbilitiesS2CPacket;

public class PlayerAbilitiesS2CPacketHandler implements BasePacketHandler<PlayerAbilitiesS2CPacket> {
    @Override
    public String name() {
        return "PlayerAbilities";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Player_Abilities";
    }

    @Override
    public JsonObject serialize(PlayerAbilitiesS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("invulnerable", packet.isInvulnerable());
        jsonObject.addProperty("flying", packet.isFlying());
        jsonObject.addProperty("allowFlying", packet.allowFlying());
        jsonObject.addProperty("creativeMode", packet.isCreativeMode());
        jsonObject.addProperty("flySpeed", packet.getFlySpeed());
        jsonObject.addProperty("walkSpeed", packet.getWalkSpeed());
        return jsonObject;
    }
}
