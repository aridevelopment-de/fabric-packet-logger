package de.ari24.packetlogger.mixin;

import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets={"net/minecraft/network/packet/c2s/play/PlayerInteractEntityC2SPacket$InteractAtHandler"})
public interface PlayerInteractEntityC2SPacketInteractAtHandlerAccessor {
    @Accessor
    Hand getHand();

    @Accessor
    Vec3d getPos();
}
