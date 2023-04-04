package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.OpenScreenS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class OpenScreenS2CPacketHandler implements BasePacketHandler<OpenScreenS2CPacket> {
    @Override
    public String id() {
        return "OpenScreen";
    }

    @Override
    public JsonObject serialize(OpenScreenS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("syncId", packet.getSyncId());

        if (packet.getScreenHandlerType() != null) {
            Identifier id = Registries.SCREEN_HANDLER.getId(packet.getScreenHandlerType());

            if (id != null) {
                jsonObject.addProperty("screenHandlerType", id.toString());
            }
        }

        jsonObject.addProperty("name", packet.getName().toString());
        return jsonObject;
    }
}
