package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntityEquipmentUpdateS2CPacket;

import java.util.List;

public class EntityEquipmentUpdateS2CPacketHandler implements BasePacketHandler<EntityEquipmentUpdateS2CPacket> {
    @Override
    public String id() {
        return "SetEquipment";
    }

    private JsonObject serializeEquipment(List<Pair<EquipmentSlot, ItemStack>> equipment) {
        JsonObject jsonObject = new JsonObject();

        equipment.forEach(pair -> {
            jsonObject.addProperty(pair.getFirst().getName(), pair.getSecond().toString());
        });

        return jsonObject;
    }

    @Override
    public JsonObject serialize(EntityEquipmentUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("entityId", packet.getId());
        jsonObject.add("equipment", serializeEquipment(packet.getEquipmentList()));

        return jsonObject;
    }
}
