package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.OpenWrittenBookS2CPacket;

public class OpenWrittenBookS2CPacketHandler implements BasePacketHandler<OpenWrittenBookS2CPacket> {

    @Override
    public JsonObject serialize(OpenWrittenBookS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("hand", packet.getHand().name());
        return jsonObject;
    }
}
