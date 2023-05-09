package de.ari24.packetlogger.packets.clientbound;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.packets.BasePacketHandler;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.PlayerRespawnS2CPacket;

public class PlayerRespawnS2CPacketHandler implements BasePacketHandler<PlayerRespawnS2CPacket> {

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
        jsonObject.addProperty("keepAttributes", packet.hasFlag(PlayerRespawnS2CPacket.KEEP_ATTRIBUTES));
        jsonObject.addProperty("keepTrackedData", packet.hasFlag(PlayerRespawnS2CPacket.KEEP_TRACKED_DATA));
        jsonObject.addProperty("keepAll", packet.hasFlag(PlayerRespawnS2CPacket.KEEP_ALL));
        jsonObject.addProperty("hasDeathLocation", packet.getLastDeathPos().isPresent());

        if (packet.getLastDeathPos().isPresent()) {
            jsonObject.add("deathLocation", ConvertUtils.serializeGlobalPos(packet.getLastDeathPos().get()));
        }

        return jsonObject;
    }
}
