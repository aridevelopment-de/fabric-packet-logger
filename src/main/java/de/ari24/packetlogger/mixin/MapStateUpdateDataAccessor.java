package de.ari24.packetlogger.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets={"net.minecraft.item.map.MapState$UpdateData"})
public interface MapStateUpdateDataAccessor {
    @Accessor
    int getStartX();

    @Accessor
    int getStartZ();

    @Accessor
    int getWidth();

    @Accessor
    int getHeight();

    @Accessor
    byte[] getColors();
}
