package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.c2s.play.ButtonClickC2SPacket;

import java.util.List;

public class ButtonClickC2SPacketHandler implements BasePacketHandler<ButtonClickC2SPacket> {
    @Override
    public List<String> getJsonParameterOrder() {
        return List.of("windowId", "buttonId");
    }

    @Override
    public JsonObject serialize(ButtonClickC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("windowId", packet.getSyncId());
        jsonObject.addProperty("buttonId", packet.getButtonId());
        return jsonObject;
    }
}
