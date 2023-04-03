package de.ari24.packetlogger.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.SectionHeader;

@Modmenu(modId="packetlogger")
@Config(name="packetlogger-config", wrapperName="PacketLoggerConfig")
public class PacketLoggerConfigModel {
    @SectionHeader("General")
    public boolean logPackets = false;
    public boolean sysOutUnknownPackets = false;
    public int wssPort = 1337;
    public int webserverPort = 8080;
}