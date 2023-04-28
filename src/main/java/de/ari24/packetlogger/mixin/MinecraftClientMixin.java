package de.ari24.packetlogger.mixin;

import de.ari24.packetlogger.PacketLogger;
import de.ari24.packetlogger.ui.PacketLoggerDataWarningScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Redirect(
            method= "<init>(Lnet/minecraft/client/RunArgs;)V",
            at=@At(value="INVOKE", target="Lnet/minecraft/client/MinecraftClient;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V")
    )
    private void PacketLogger$showWarningScreen(MinecraftClient instance, Screen screen) {
        if (!(screen instanceof TitleScreen) || !PacketLogger.CONFIG.showDataWarning()) {
            instance.setScreen(screen);
            return;
        }

        instance.setScreen(new PacketLoggerDataWarningScreen(screen));
    }
}
