package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.GameStateChangeS2CPacketReasonAccessor;
import de.ari24.packetlogger.packets.BasePacketHandler;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.world.GameMode;

public class GameStateChangeS2CPacketHandler implements BasePacketHandler<GameStateChangeS2CPacket> {
    @Override
    public String name() {
        return "GameEvent";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Game_Event";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Used for a wide variety of game events, from weather to bed use to gamemode to demo messages.");
        jsonObject.addProperty("wikiVgNotes", """
                Notes on some events:
                - No Respawn Block Available: Displays message 'block.minecraft.spawn.not_valid' (You have no home bed or charged respawn anchor, or it was obstructed) to the player.
                - Arrow hit player: Sent when any player is struck by an arrow.
                - Rain level change: Seems to change both sky color and lighting. Ranges from 0 to 1
                - Thunder level change: Seems to change both sky color and lighting (same as Rain level change, but doesn't start rain). It also requires rain to render by notchian client. Ranges from 0 to 1
                """);
        jsonObject.addProperty("event", "The event name if resolvable, otherwise the id");
        jsonObject.addProperty("value", "The event value if resolvable, otherwise the float");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(GameStateChangeS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();

        GameStateChangeS2CPacketReasonAccessor accessor = (GameStateChangeS2CPacketReasonAccessor) packet.getReason();
        String event = null;
        String value = null;

        switch (accessor.getId()) {
            case 0 ->
                event = "No Respawn Block Available";
            case 1 ->
                event = "Begin raining"; /* TODO: here id is 1 but in wiki.vg is 2 wtf */
            case 2 ->
                event = "Stop raining";
            case 3 -> {
                event = "Change gamemode";
                value = GameMode.byId((int) packet.getValue()).asString();
            }
            case 4 -> {
                event = "Win game";

                switch ((int) packet.getValue()) {
                    case 0 -> value = "Just respawn player. If \"The End?\" is already achieved, this value will always be used.";
                    case 1 -> value = "Roll the credits and respawn player. Achievement \"The End?\" is granted.";
                }
            }
            case 5 -> {
                event = "Demo event";

                switch ((int) packet.getValue()) {
                    case 0 -> value = "Show welcome to demo screen";
                    case 101 -> value = "Tell movement controls.";
                    case 102 -> value = "Tell jump control.";
                    case 103 -> value = "Tell inventory control.";
                    case 104 -> value = "Tell that the demo is over and print a message about how to take a screenshot";
                }
            }
            case 6 ->
                event = "Arrow hit player";
            case 7 ->
                event = "Rain level change";
            case 8 ->
                event = "Thunder level change";
            case 9 ->
                event = "Play pufferfish sting sound";
            case 10 ->
                event = "Play elder guardian mob appearance (effect and sound)";
            case 11 -> {
                event = "Enable respawn screen";

                switch ((int) packet.getValue()) {
                    case 0 -> value = "Enable respawn screen.";
                    case 1 -> value = "Immediately respawn (sent when doImmediateRespawn gamerule changes).";
                }
            }
        }

        if (event != null) {
            jsonObject.addProperty("event", event);
        } else {
            jsonObject.addProperty("event", accessor.getId());
        }

        if (value != null) {
            jsonObject.addProperty("value", value);
        } else {
            jsonObject.addProperty("value", packet.getValue());
        }

        return jsonObject;
    }
}
