package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Set;

public class GameJoinS2CPacketHandler implements BasePacketHandler<GameJoinS2CPacket> {
    private JsonObject serializeDimensionIds(Set<RegistryKey<World>> dimensionIds) {
        ArrayList<String> ids = new ArrayList<>();

        for (RegistryKey<World> dimensionId : dimensionIds) {
            ids.add(dimensionId.getValue().toString());
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("ids", ConvertUtils.GSON_INSTANCE.toJsonTree(ids));
        return jsonObject;
    }

    @Override
    public String name() {
        return "Login (Play)";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Login_.28play.29";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Being sent after the client has successfully logged in (play). This packet is used to inform the client about basic metadata.");
        jsonObject.addProperty("wikiVgNotes", """
                - Logging in: https://wiki.vg/Protocol_Encryption
                - Debug Screen: https://minecraft.fandom.com/wiki/Debug_screen
                - Debug World: https://minecraft.fandom.com/wiki/Debug_mode
                - Flat World: https://minecraft.fandom.com/wiki/Superflat
                """);
        jsonObject.addProperty("entityId", "The entity ID of the player (s.t. the client knows its own id).");
        jsonObject.addProperty("hardcore", "True if the server is in hardcore mode.");
        jsonObject.addProperty("gamemode", "The gamemode of the player.");
        jsonObject.addProperty("previousGamemode", "-1: Undefined (null), 0: Survival, 1: Creative, 2: Adventure, 3: Spectator. The previous gamemode. Vanilla client uses this for the debug (F3 + N & F3 + F4) gamemode switch.");
        jsonObject.addProperty("dimensionIds", "Identifiers for all dimensions (worlds) on the server. ");
        jsonObject.addProperty("dimensionType", "Name of the dimension type being spawned into.");
        jsonObject.addProperty("dimensionId", "Name of the dimension being spawned into.");
        jsonObject.addProperty("sha256Seed", "First 8 bytes of the SHA-256 hash of the world's seed. Used client side for biome noise.");
        jsonObject.addProperty("maxPlayers", "Was once used by the client to draw the player list, but now is ignored.");
        jsonObject.addProperty("viewDistance", "Render distance (2-32).");
        jsonObject.addProperty("simulationDistance", "The distance that the client will process specific things, such as entities");
        jsonObject.addProperty("reducedDebugInfo", "If true, a Notchian client shows reduced information on the debug screen. For servers in development, this should almost always be false. ");
        jsonObject.addProperty("enableRespawnScreen", "Set to false when the doImmediateRespawn gamerule is true.");
        jsonObject.addProperty("debugWorld", "True if the world is a debug mode world; debug mode worlds cannot be modified and have predefined blocks.");
        jsonObject.addProperty("flatWorld", "True if the world is a flat world; flat worlds have a fixed height and no terrain generation.");
        jsonObject.addProperty("deathLocation", "The location of the player's last death. If the player has not died, this is null.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(GameJoinS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.playerEntityId());
        jsonObject.addProperty("hardcore", packet.hardcore());
        jsonObject.addProperty("gamemode", packet.gameMode().asString());
        if (packet.previousGameMode() != null) {
            jsonObject.addProperty("previousGamemode", packet.previousGameMode().asString());
        } else {
            jsonObject.add("previousGamemode", JsonNull.INSTANCE);
        }
        jsonObject.add("dimensionIds", serializeDimensionIds(packet.dimensionIds()));
        jsonObject.addProperty("dimensionType", packet.dimensionType().getValue().toString());
        jsonObject.addProperty("dimensionId", packet.dimensionId().getValue().toString());
        jsonObject.addProperty("sha256Seed", packet.sha256Seed());
        jsonObject.addProperty("maxPlayers", packet.maxPlayers());
        jsonObject.addProperty("viewDistance", packet.viewDistance());
        jsonObject.addProperty("simulationDistance", packet.simulationDistance());
        jsonObject.addProperty("reducedDebugInfo", packet.reducedDebugInfo());
        jsonObject.addProperty("enableRespawnScreen", packet.showDeathScreen());
        jsonObject.addProperty("isDebug", packet.debugWorld());
        jsonObject.addProperty("isFlat", packet.flatWorld());

        if (packet.lastDeathLocation().isPresent()) {
            jsonObject.add("deathLocation", ConvertUtils.serializeGlobalPos(packet.lastDeathLocation().get()));
        } else {
            jsonObject.add("deathLocation", JsonNull.INSTANCE);
        }

        return jsonObject;
    }
}
