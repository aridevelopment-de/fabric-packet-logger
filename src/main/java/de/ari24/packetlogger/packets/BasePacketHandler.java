package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.packet.Packet;

public interface BasePacketHandler<T extends Packet<?>> {
    String id();
    JsonObject serialize(T packet);
}
