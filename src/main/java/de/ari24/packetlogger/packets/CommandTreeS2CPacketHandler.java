package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.CommandTreeS2CPacketAccessor;
import net.minecraft.network.packet.s2c.play.CommandTreeS2CPacket;

public class CommandTreeS2CPacketHandler implements BasePacketHandler<CommandTreeS2CPacket> {
    @Override
    public String name() {
        return "Commands";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Commands";
    }

    @Override
    public JsonObject serialize(CommandTreeS2CPacket packet) {
        CommandTreeS2CPacketAccessor accessor = (CommandTreeS2CPacketAccessor) packet;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("rootIndex", accessor.getRootSize());
        jsonObject.addProperty("nodes", "TODO: https://haste.pinofett.de/oyaoftvqxs.swift");
        return jsonObject;
    }
}
