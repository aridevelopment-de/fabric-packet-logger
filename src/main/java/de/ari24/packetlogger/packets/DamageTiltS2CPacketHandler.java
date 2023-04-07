package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.DamageTiltS2CPacket;

public class DamageTiltS2CPacketHandler implements BasePacketHandler<DamageTiltS2CPacket> {

    @Override
    public String name() {
        return "HurtAnimation";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Hurt_Animation";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "This packet is sent when an entity is hurt. It plays a bobbing animation for the entity receiving damage.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("entityId", "The entity ID of the entity receiving damage.");
        jsonObject.addProperty("yaw", "The direction the damage is coming from in relation to the entity.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(DamageTiltS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.id());
        jsonObject.addProperty("yaw", packet.yaw());
        return jsonObject;
    }
}
