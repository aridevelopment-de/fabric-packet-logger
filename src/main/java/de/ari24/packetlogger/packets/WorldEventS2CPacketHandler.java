package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.client.particle.Particle;
import net.minecraft.network.packet.s2c.play.WorldEventS2CPacket;

import java.util.HashMap;
import java.util.Map;

public class WorldEventS2CPacketHandler implements BasePacketHandler<WorldEventS2CPacket> {

    private static final Map<Integer, String> EVENT_MAP = new HashMap<>();
    private static final Map<Integer, String> DIRECTIONS = Map.of(0, "DOWN", 1, "UP", 2, "NORTH", 3, "SOUTH", 4, "WEST", 5, "EAST");

    static {
        // Sounds
        EVENT_MAP.put(1000, "Dispenser dispenses");
        EVENT_MAP.put(1001, "Dispenser fails to dispense");
        EVENT_MAP.put(1002, "Dispenser shoots");
        EVENT_MAP.put(1003, "Ender eye launched");
        EVENT_MAP.put(1004, "Firework shot");
        EVENT_MAP.put(1005, "Iron door opened");
        EVENT_MAP.put(1006, "Wooden door opened");
        EVENT_MAP.put(1007, "Wooden trapdoor opened");
        EVENT_MAP.put(1008, "Fence gate opened");
        EVENT_MAP.put(1009, "Fire extinguished");
        EVENT_MAP.put(1010, "Play record");
        EVENT_MAP.put(1011, "Iron door closed");
        EVENT_MAP.put(1012, "Wooden door closed");
        EVENT_MAP.put(1013, "Wooden trapdoor closed");
        EVENT_MAP.put(1014, "Fence gate closed");
        EVENT_MAP.put(1015, "Ghast warns");
        EVENT_MAP.put(1016, "Ghast shoots");
        EVENT_MAP.put(1017, "Enderdragon shoots");
        EVENT_MAP.put(1018, "Blaze shoots");
        EVENT_MAP.put(1019, "Zombie attacks wood door");
        EVENT_MAP.put(1020, "Zombie attacks iron door");
        EVENT_MAP.put(1021, "Zombie breaks wood door");
        EVENT_MAP.put(1022, "Wither breaks block");
        EVENT_MAP.put(1023, "Wither spawned");
        EVENT_MAP.put(1024, "Wither shoots");
        EVENT_MAP.put(1025, "Bat takes off");
        EVENT_MAP.put(1026, "Zombie infects");
        EVENT_MAP.put(1027, "Zombie villager converted");
        EVENT_MAP.put(1028, "Ender dragon death");
        EVENT_MAP.put(1029, "Anvil destroyed");
        EVENT_MAP.put(1030, "Anvil used");
        EVENT_MAP.put(1031, "Anvil landed");
        EVENT_MAP.put(1032, "Portal travel");
        EVENT_MAP.put(1033, "Chorus flower grown");
        EVENT_MAP.put(1034, "Chorus flower died");
        EVENT_MAP.put(1035, "Brewing stand brewed");
        EVENT_MAP.put(1036, "Iron trapdoor opened");
        EVENT_MAP.put(1037, "Iron trapdoor closed");
        EVENT_MAP.put(1038, "End portal created in overworld");
        EVENT_MAP.put(1039, "Phantom bites");
        EVENT_MAP.put(1040, "Zombie converts to drowned");
        EVENT_MAP.put(1041, "Husk converts to zombie by drowning");
        EVENT_MAP.put(1042, "Grindstone used");
        EVENT_MAP.put(1043, "Book page turned");
        // Particles
        EVENT_MAP.put(1500, "Composter composts");
        EVENT_MAP.put(1501, "Lava converts block (either water to stone, or removes existing blocks such as torches)");
        EVENT_MAP.put(1502, "Redstone torch burns out");
        EVENT_MAP.put(1503, "Ender eye placed");
        EVENT_MAP.put(2000, "Spawns 10 smoke particles, e.g. from a fire");
        EVENT_MAP.put(2001, "Block break + block break sound");
        EVENT_MAP.put(2002, "Splash potion. Particle effect + glass break sound.");
        EVENT_MAP.put(2003, "Eye of Ender entity break animation — particles and sound");
        EVENT_MAP.put(2004, "Mob spawn particle effect: smoke + flames");
        EVENT_MAP.put(2005, "Bonemeal particles");
        EVENT_MAP.put(2006, "Dragon breath");
        EVENT_MAP.put(2007, "Instant splash potion. Particle effect + glass break sound");
        EVENT_MAP.put(2008, "Ender dragon destroys block");
        EVENT_MAP.put(2009, "Wet sponge vaporizes in nether");
        EVENT_MAP.put(3000, "End gateway spawn");
        EVENT_MAP.put(3001, "Enderdragon growl");
        EVENT_MAP.put(3002, "Electric spark");
        EVENT_MAP.put(3003, "Copper apply wax");
        EVENT_MAP.put(3004, "Copper remove wax");
        EVENT_MAP.put(3005, "Copper scrape oxidation");
    }

    @Override
    public String id() {
        return "WorldEvent";
    }

    private JsonObject getEventData(int eventId, int data) {
        JsonObject jsonObject = new JsonObject();

        switch (eventId) {
            case 2000:
                jsonObject.addProperty("smokeDirection", DIRECTIONS.getOrDefault(data, "Unknown"));
            case 2001:
                jsonObject.addProperty("blockStateIndex", data);
            case 2002:
                jsonObject.addProperty("rgbColor", ConvertUtils.convertRGB(data));
            case 2005:
                jsonObject.addProperty("particleCount", data == 0 ? 15 : data);
            case 2007:
                jsonObject.addProperty("rgbColor", ConvertUtils.convertRGB(data));
            case 1010:
                jsonObject.addProperty("recordId", data);
        }

        return jsonObject;
    }

    @Override
    public JsonObject serialize(WorldEventS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("eventId", packet.getEventId());
        jsonObject.addProperty("eventName", EVENT_MAP.getOrDefault(packet.getEventId(), "Unknown"));
        jsonObject.addProperty("pos", packet.getPos().toString());
        jsonObject.addProperty("rawData", packet.getData());
        jsonObject.add("data", getEventData(packet.getEventId(), packet.getData()));
        jsonObject.addProperty("isGlobal", packet.isGlobal());
        return jsonObject;
    }
}
