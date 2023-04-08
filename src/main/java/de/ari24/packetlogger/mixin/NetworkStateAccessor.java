package de.ari24.packetlogger.mixin;

import net.minecraft.network.NetworkSide;
import net.minecraft.network.NetworkState;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(NetworkState.class)
public interface NetworkStateAccessor {
    @Accessor
    static Map<Class<? extends Packet<?>>, NetworkState> getHANDLER_STATE_MAP() {
        throw new UnsupportedOperationException();
    }
}
