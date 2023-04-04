package de.ari24.packetlogger.mixin;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(targets="net.minecraft.registry.tag.TagPacketSerializer$Serialized")
public interface TagPacketSerializerSerializedAccessor {
    @Accessor
    Map<Identifier, IntList> getContents();
}
