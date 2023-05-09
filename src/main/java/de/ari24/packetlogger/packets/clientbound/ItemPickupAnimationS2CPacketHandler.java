package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.ItemPickupAnimationS2CPacket;

public class ItemPickupAnimationS2CPacketHandler implements BasePacketHandler<ItemPickupAnimationS2CPacket> {

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
