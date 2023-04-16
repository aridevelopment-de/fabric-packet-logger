package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.PlayerRespawnS2CPacket;

public class PlayerRespawnS2CPacketHandler implements BasePacketHandler<PlayerRespawnS2CPacket> {
    @Override
    public String name() {
        return "Respawn";
    }

    @Override
    public String url() {
        return "https://wiki.vg/index.php?title=Protocol&oldid=18067#Respawn";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "To change the player's dimension (overworld/nether/end), send them a respawn packet with the appropriate dimension, followed by prechunks/chunks for the new dimension, and finally a position and look packet. You do not need to unload chunks, the client will do it automatically.");
        jsonObject.addProperty("wikiVgNotes", "Avoid changing player's dimension to same dimension they were already in unless they are dead. If you change the dimension to one they are already in, weird bugs can occur, such as the player being unable to attack other players in new world (until they die and respawn). Before 1.16, if you must respawn a player in the same dimension without killing them, send two respawn packets, one to a different world and then another to the world you want. You do not need to complete the first respawn; it only matters that you send two packets. ");
        jsonObject.addProperty("dimensionType", "Valid dimensions are defined per dimension registry sent in Login (play)");
        jsonObject.addProperty("dimensionName", "Name of the dimension being spawned into");
        jsonObject.addProperty("hashedSeed", "First 8 bytes of the SHA-256 hash of the world's seed. Used client side for biome noise");
        jsonObject.addProperty("gamemode", "New gamemode of the player");
        jsonObject.addProperty("previousGamemode", "Vanilla client uses this for the debug (F3 + N & F3 + F4) gamemode switch");
        jsonObject.addProperty("isDebug", "True if the world is a debug mode world; debug mode worlds cannot be modified and have predefined blocks.");
        jsonObject.addProperty("isFlat", "True if the world is a flat world; flat worlds have a fixed height and no terrain generation.");
        jsonObject.addProperty("keepAttributes", "True if the player's attributes should be kept. If false, the client will reset the player's attributes to their default values. Wiki.vg seems to conclude these three flags into one 'copyMetadata' boolean");
        jsonObject.addProperty("keepTrackedData", "True if the player's tracked data should be kept. If false, the client will reset the player's tracked data to their default values.");
        jsonObject.addProperty("keepAll", "True if the player's attributes and tracked data should be kept. If false, the client will reset the player's attributes and tracked data to their default values.");
        jsonObject.addProperty("deathLocation", "The location of the player's last death. If the player has not died, this is null.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(PlayerRespawnS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("dimensionType", packet.getDimensionType().toString());
        jsonObject.addProperty("dimensionName", packet.getDimension().toString());
        jsonObject.addProperty("hashedSeed", packet.getSha256Seed());
        jsonObject.addProperty("gamemode", packet.getGameMode().asString());

        if (packet.getPreviousGameMode() != null) {
            jsonObject.addProperty("previousGamemode", packet.getPreviousGameMode().asString());
        } else {
            jsonObject.add("previousGamemode", JsonNull.INSTANCE);
        }

        jsonObject.addProperty("isDebug", packet.isDebugWorld());
        jsonObject.addProperty("isFlat", packet.isFlatWorld());
        // TODO: wiki.vg states a "copyMetadata" boolean, but it's not in the packet class
        // Is the flag attribute used for this?
        jsonObject.addProperty("keepAttributes", packet.method_48016(PlayerRespawnS2CPacket.field_41730));
        jsonObject.addProperty("keepTrackedData", packet.method_48016(PlayerRespawnS2CPacket.field_41731));
        jsonObject.addProperty("keepAll", packet.method_48016(PlayerRespawnS2CPacket.field_41732));
        jsonObject.addProperty("hasDeathLocation", packet.getLastDeathPos().isPresent());

        if (packet.getLastDeathPos().isPresent()) {
            jsonObject.add("deathLocation", ConvertUtils.serializeGlobalPos(packet.getLastDeathPos().get()));
        }

        return jsonObject;
    }
}
