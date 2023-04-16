package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;

public class WorldTimeUpdateS2CPacketHandler implements BasePacketHandler<WorldTimeUpdateS2CPacket> {
    @Override
    public String name() {
        return "UpdateTime";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Update_Time";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Updates the current time on the world. Can be set via /time set <value>");
        jsonObject.addProperty("wikiVgNotes", """
                Time is based on ticks, where 20 ticks happen every second. There are 24000 ticks in a day, making Minecraft days exactly 20 minutes long.
                The time of day is based on the timestamp modulo 24000. 0 is sunrise, 6000 is noon, 12000 is sunset, and 18000 is midnight.
                The default SMP server increments the time by 20 every second.\s
                """);
        jsonObject.addProperty("worldAge", "In ticks; not changed by server commands. ");
        jsonObject.addProperty("timeOfDay", "The world (or region) time, in ticks. If negative the sun will stop moving at the Math.abs of the time. ");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(WorldTimeUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("worldAge", packet.getTime());
        jsonObject.addProperty("timeOfDay", packet.getTimeOfDay());
        return jsonObject;
    }
}
