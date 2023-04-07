package de.ari24.packetlogger.mixin;

import net.minecraft.network.packet.s2c.play.SetCameraEntityS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SetCameraEntityS2CPacket.class)
public interface SetCameraEntityS2CPacketAccessor {
    @Accessor
    int getEntityId();
}
