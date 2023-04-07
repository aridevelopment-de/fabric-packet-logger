package de.ari24.packetlogger.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets="net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket$Reason")
public interface GameStateChangeS2CPacketReasonAccessor {
    @Accessor
    int getId();
}
