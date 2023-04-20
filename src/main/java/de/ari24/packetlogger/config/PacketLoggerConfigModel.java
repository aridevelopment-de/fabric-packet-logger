package de.ari24.packetlogger.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Hook;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.SectionHeader;

@Modmenu(modId="packetlogger")
@Config(name="packetlogger-config", wrapperName="PacketLoggerConfig")
public class PacketLoggerConfigModel {
    @SectionHeader("General")
    @Hook
    public LogState logState = LogState.OFF;
    public int maxPacketsPerSecond = 1000;
    public boolean resolveEntityIdsToEntities = false;
    public int wssPort = 1337;
    public int webserverPort = 8080;

    public enum LogState {
        OFF,
        LOGGING
    }
}
