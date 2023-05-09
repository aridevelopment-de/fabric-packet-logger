package de.ari24.packetlogger.packets.serverbound;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.message.ArgumentSignatureDataMap;
import net.minecraft.network.packet.c2s.play.CommandExecutionC2SPacket;

public class CommandExecutionC2SPacketHandler implements BasePacketHandler<CommandExecutionC2SPacket> {
    @Override
    public JsonObject serialize(CommandExecutionC2SPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("command", packet.command());
        jsonObject.addProperty("timestamp", packet.timestamp().toString());
        jsonObject.addProperty("salt", packet.salt());

        JsonArray jsonArray = new JsonArray();

        for (ArgumentSignatureDataMap.Entry entry : packet.argumentSignatures().entries()) {
            JsonObject object = new JsonObject();
            object.addProperty("name", entry.name());
            object.addProperty("signature", entry.signature().toString());
            jsonArray.add(object);
        }

        jsonObject.add("argumentSignatures", jsonArray);
        jsonObject.addProperty("messageCount", packet.acknowledgment().offset());
        jsonObject.addProperty("acknowledged", packet.acknowledgment().acknowledged().toString());
        return jsonObject;
    }
}
