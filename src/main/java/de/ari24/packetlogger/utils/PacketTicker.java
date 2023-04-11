package de.ari24.packetlogger.utils;

import java.time.Instant;

public class PacketTicker {


    private int sum = 0;
    private int PPS;
    private Instant lastReset;

    public PacketTicker() {
        reset();
    }

    public void tick() {
        validate();
        this.sum += 1;
    }

    public void validate() {
        if (Instant.now().toEpochMilli() - this.lastReset.toEpochMilli() >= 1000) {
            reset();
        }
    }
    public void reset() {
        this.PPS = sum;
        this.sum = 0;
        this.lastReset = Instant.now();
    }

    public int getPPS() {
        validate();
        return this.PPS;
    }
}
