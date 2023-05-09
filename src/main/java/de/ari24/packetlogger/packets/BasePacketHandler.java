package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.Packet;

public interface BasePacketHandler<T extends Packet<?>> {
    JsonObject serialize(T packet);
}
