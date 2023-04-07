package de.ari24.packetlogger.mixin;

import net.minecraft.network.packet.s2c.play.CommandTreeS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CommandTreeS2CPacket.class)
public interface CommandTreeS2CPacketAccessor {
    @Accessor
    int getRootSize();
}
