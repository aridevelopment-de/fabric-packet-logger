package de.ari24.packetlogger.mixin;

import net.minecraft.network.packet.s2c.play.EndCombatS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EndCombatS2CPacket.class)
public interface EndCombatS2CPacketAccessor {
    @Accessor
    int getAttackerId();

    @Accessor
    int getTimeSinceLastAttack();
}
