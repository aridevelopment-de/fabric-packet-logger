package de.ari24.packetlogger.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.network.packet.s2c.play.ChunkDeltaUpdateS2CPacket;
import net.minecraft.util.math.ChunkSectionPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ChunkDeltaUpdateS2CPacket.class)
public interface ChunkDeltaS2CPacketAccessor {
    @Accessor
    ChunkSectionPos getSectionPos();

    @Accessor
    BlockState[] getBlockStates();
}
