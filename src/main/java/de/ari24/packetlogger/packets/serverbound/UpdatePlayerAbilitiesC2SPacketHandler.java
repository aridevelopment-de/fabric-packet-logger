package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.UpdatePlayerAbilitiesC2SPacket;

public class UpdatePlayerAbilitiesC2SPacketHandler implements BasePacketHandler<UpdatePlayerAbilitiesC2SPacket> {
    @Override
    public JsonObject serialize(UpdatePlayerAbilitiesC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("flying", packet.isFlying());
        return jsonObject;
    }
}
