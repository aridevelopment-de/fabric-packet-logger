package de.ari24.packetlogger.mixin;

import net.minecraft.network.packet.c2s.play.SpectatorTeleportC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.UUID;

@Mixin(SpectatorTeleportC2SPacket.class)
public interface SpectatorTeleportC2SPacketAccessor {
    @Accessor
    UUID getTargetUuid();
}
