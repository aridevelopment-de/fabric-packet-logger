package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.TitleFadeS2CPacket;

public class TitleFadeS2CPacketHandler implements BasePacketHandler<TitleFadeS2CPacket> {


    @Override
    public JsonObject serialize(TitleFadeS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("fadeIn", packet.getFadeInTicks());
        jsonObject.addProperty("stay", packet.getStayTicks());
        jsonObject.addProperty("fadeOut", packet.getFadeOutTicks());
        return jsonObject;
    }
}
