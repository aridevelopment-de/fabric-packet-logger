package de.ari24.packetlogger.mixin;

import net.minecraft.network.packet.c2s.login.LoginKeyC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LoginKeyC2SPacket.class)
public interface LoginKeyC2SPacketAccessor {
    @Accessor
    byte[] getEncryptedSecretKey();

    @Accessor
    byte[] getNonce();
}
