package de.ari24.packetlogger.mixin;

import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets={"net/minecraft/network/packet/c2s/play/PlayerInteractEntityC2SPacket$InteractHandler"})
public interface PlayerInteractEntityC2SPacketInteractHandlerAccessor {
    @Accessor
    Hand getHand();
}
