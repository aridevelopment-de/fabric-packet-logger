package de.ari24.packetlogger.mixin;

import net.minecraft.network.packet.c2s.play.UpdateCommandBlockMinecartC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(UpdateCommandBlockMinecartC2SPacket.class)
public interface UpdateCommandBlockMinecartC2SPacketAccessor {
    @Accessor
    int getEntityId();
}
