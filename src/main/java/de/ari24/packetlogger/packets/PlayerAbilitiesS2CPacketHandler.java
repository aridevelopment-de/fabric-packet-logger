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
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Player_Abilities";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet is sent to the client to update the player's abilities. This includes whether the player is invulnerable, flying, and whether they can fly. It also includes the player's current fly and walk speeds.");
        jsonObject.addProperty("wikiVgNotes", "The latter 2 floats are used to indicate the flying speed and field of view respectively, while the first byte is used to determine the value of 4 booleans. ");
        jsonObject.addProperty("invulnerable", "Whether the player is invulnerable to damage.");
        jsonObject.addProperty("flying", "Whether the player is flying.");
        jsonObject.addProperty("allowFlying", "Whether the player is allowed to fly.");
        jsonObject.addProperty("creativeMode", "Whether the player is in creative mode.");
        jsonObject.addProperty("flySpeed", "The player's current flying speed. Default: 0.05");
        jsonObject.addProperty("fieldOfViewModifier", "Modifies the field of view, like a speed potion. A Notchian server will use the same value as the movement speed sent in the Update Attributes packet, which defaults to 0.1 for players. ");
        return jsonObject;
    }

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
