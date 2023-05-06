package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.c2s.play.CreativeInventoryActionC2SPacket;

public class CreativeInventoryActionC2SPacketHandler implements BasePacketHandler<CreativeInventoryActionC2SPacket> {
    @Override
    public JsonObject serialize(CreativeInventoryActionC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("slot", packet.getSlot());
        jsonObject.add("stack", ConvertUtils.serializeItemStack(packet.getItemStack()));
        return jsonObject;
    }
}
