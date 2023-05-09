package de.ari24.packetlogger.ui;

import de.ari24.packetlogger.PacketLogger;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.WarningScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Objects;

public class PacketLoggerDataWarningScreen extends WarningScreen {
    private static final Text HEADER;
    private static final Text MESSAGE;
    private static final Text CHECK_MESSAGE;
    private static final Text NARRATED_TEXT;
    private final Screen parent;

    public PacketLoggerDataWarningScreen(Screen parent) {
        super(HEADER, MESSAGE, CHECK_MESSAGE, NARRATED_TEXT);
        this.parent = parent;
    }

    @Override
    protected void initButtons(int yOffset) {
        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.PROCEED, (buttonWidget) -> {
            if (Objects.requireNonNull(this.checkbox).isChecked()) {
                PacketLogger.CONFIG.showDataWarning(false);
            }

            Objects.requireNonNull(this.client).setScreen(this.parent);
        }).dimensions(this.width / 2 - 155 + 80, 100 + yOffset, 150, 20).build());
    }

    static {
        HEADER = Text.translatable("dataWarning.header").formatted(Formatting.BOLD);
        MESSAGE = Text.translatable("dataWarning.message");
        CHECK_MESSAGE = Text.translatable("dataWarning.check");
        NARRATED_TEXT = HEADER.copy().append("\n").append(MESSAGE);
    }
}
