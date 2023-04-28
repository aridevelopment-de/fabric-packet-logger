package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
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
