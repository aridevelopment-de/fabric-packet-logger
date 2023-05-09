package de.ari24.packetlogger.mixin;

import de.ari24.packetlogger.PacketLogger;
import de.ari24.packetlogger.config.PacketLoggerConfigModel;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static de.ari24.packetlogger.PacketLogger.PACKET_TICKER;

@Mixin(value = DebugHud.class, priority = 1337)
public class DebugHudMixin {
    @Inject(method = "getLeftText()Ljava/util/List;", at = @At("TAIL"))
    protected void PacketLogger$injectGetLeftText(CallbackInfoReturnable<List<String>> cir) {
        List<String> value = cir.getReturnValue();
        value.add("");
        value.add(Formatting.GOLD +"[Packet Logger]");
        value.add("Logging packets: " + PacketLogger.CONFIG.logState().name().toLowerCase());
        value.add("Connected clients: " + PacketLogger.wss.getClients().size());
        value.add((PacketLogger.CONFIG.logState() == PacketLoggerConfigModel.LogState.LOGGING ? "Packets/s: " + PACKET_TICKER.getPPS() : ""));
        value.add("");
    }
}
