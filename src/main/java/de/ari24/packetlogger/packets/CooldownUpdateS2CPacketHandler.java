package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.CooldownUpdateS2CPacket;

public class CooldownUpdateS2CPacketHandler implements BasePacketHandler<CooldownUpdateS2CPacket> {
    @Override
    public String name() {
        return "SetCooldown";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Set_Cooldown";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Applies a cooldown period to all items with the given type. Used by the Notchian server with enderpearls. This packet should be sent when the cooldown starts and also when the cooldown ends (to compensate for lag), although the client will end the cooldown automatically.");
        jsonObject.addProperty("wikiVgNotes", "Can be applied to any item, note that interactions still get sent to the server with the item but the client does not play the animation nor attempt to predict results (i.e block placing)");
        jsonObject.addProperty("item", "Item to apply the cooldown to");
        jsonObject.addProperty("cooldownTicks", "Number of ticks to apply a cooldown for, or 0 to clear the cooldown");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(CooldownUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("item", ConvertUtils.serializeItem(packet.getItem()));
        jsonObject.addProperty("cooldownTicks", packet.getCooldown());
        return jsonObject;
    }
}
