package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.OpenScreenS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class OpenScreenS2CPacketHandler implements BasePacketHandler<OpenScreenS2CPacket> {

    @Override
    public JsonObject serialize(OpenScreenS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("windowId", packet.getSyncId());

        if (packet.getScreenHandlerType() != null) {
            Identifier id = Registries.SCREEN_HANDLER.getId(packet.getScreenHandlerType());

            if (id != null) {
                jsonObject.addProperty("windowType", id.toString());
            }
        }

        jsonObject.addProperty("windowTitle", packet.getName().toString());
        return jsonObject;
    }
}
