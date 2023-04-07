package de.ari24.packetlogger.mixin;

import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.network.packet.s2c.play.LookAtS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LookAtS2CPacket.class)
public interface LookAtS2CPacketAccessor {
    @Accessor
    double getTargetX();

    @Accessor
    double getTargetY();

    @Accessor
    double getTargetZ();

    @Accessor
    boolean getLookAtEntity();

    @Accessor
    int getEntityId();

    @Accessor
    EntityAnchorArgumentType.EntityAnchor getTargetAnchor();
}
