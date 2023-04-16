package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.ItemPickupAnimationS2CPacket;

public class ItemPickupAnimationS2CPacketHandler implements BasePacketHandler<ItemPickupAnimationS2CPacket> {
    @Override
    public String name() {
        return "PickupItem";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Pickup_Item";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sent by the server when someone picks up an item lying on the ground");
        jsonObject.addProperty("wikiVgNotes", "its sole purpose appears to be the animation of the item flying towards you. It doesn't destroy the entity in the client memory, and it doesn't add it to your inventory. The server only checks for items to be picked up after each Set Player Position (and Set Player Position And Rotation) packet sent by the client. The collector entity can be any entity; it does not have to be a player. The collected entity also can be any entity, but the Notchian server only uses this for items, experience orbs, and the different varieties of arrows. ");
        jsonObject.addProperty("collectedEntityId", "The entity ID of the item, experience orb, or arrow that was picked up");
        jsonObject.addProperty("collecterEntityId", "The entity ID of the entity that picked up the other entity");
        jsonObject.addProperty("stackAmount", "The amount of items in the stack that was picked up. If the collected entity is an experience orb, this is always 1");
        jsonObject.addProperty("isXpOrb", "If the collected entity is an experience orb");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(ItemPickupAnimationS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.getEntityId(), "collectedEntity", "collectedEntityId");
        ConvertUtils.appendEntity(jsonObject, packet.getCollectorEntityId(), "collecterEntity", "collecterEntityId");
        jsonObject.addProperty("pickupItemCount", packet.getStackAmount());
        jsonObject.addProperty("isXpOrb", packet.getStackAmount() == 1);
        return jsonObject;
    }
}
