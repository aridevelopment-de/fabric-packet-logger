package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntityEquipmentUpdateS2CPacket;

import java.util.List;

public class EntityEquipmentUpdateS2CPacketHandler implements BasePacketHandler<EntityEquipmentUpdateS2CPacket> {


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
        ConvertUtils.appendEntity(jsonObject, packet.getId());
        jsonObject.add("equipment", serializeEquipment(packet.getEquipmentList()));

        return jsonObject;
    }
}
