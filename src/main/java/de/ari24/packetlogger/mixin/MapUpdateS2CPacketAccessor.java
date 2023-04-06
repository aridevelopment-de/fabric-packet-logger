package de.ari24.packetlogger.mixin;

import net.minecraft.item.map.MapIcon;
import net.minecraft.item.map.MapState;
import net.minecraft.network.packet.s2c.play.MapUpdateS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(MapUpdateS2CPacket.class)
public interface MapUpdateS2CPacketAccessor {
    @Accessor
    List<MapIcon> getIcons();

    @Accessor
    MapState.UpdateData getUpdateData();
}
