package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.PacketLogger;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.s2c.login.LoginCompressionS2CPacket;
import net.minecraft.network.packet.s2c.login.LoginSuccessS2CPacket;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.network.packet.s2c.query.QueryPongS2CPacket;
import net.minecraft.network.packet.s2c.query.QueryResponseS2CPacket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PacketHandler {
    private static final Map<Class<? extends Packet<?>>, BasePacketHandler<?>> HANDLERS = new HashMap<>();

    static {
        HANDLERS.put(CustomPayloadS2CPacket.class, new CustomPayloadS2CPacketHandler());
        HANDLERS.put(DifficultyS2CPacket.class, new DifficultyS2CPacketHandler());
        HANDLERS.put(FeaturesS2CPacket.class, new FeaturesS2CPacketHandler());
        HANDLERS.put(GameJoinS2CPacket.class, new GameJoinS2CPacketHandler());
        HANDLERS.put(LoginCompressionS2CPacket.class, new LoginCompressionS2CPacketHandler());
        HANDLERS.put(LoginSuccessS2CPacket.class, new LoginSuccessS2CPacketHandler());
        HANDLERS.put(PlayerAbilitiesS2CPacket.class, new PlayerAbilitiesS2CPacketHandler());
        HANDLERS.put(QueryPongS2CPacket.class, new QueryPongS2CPacketHandler());
        HANDLERS.put(QueryResponseS2CPacket.class, new QueryResponseS2CPacketHandler());
        HANDLERS.put(UpdateSelectedSlotS2CPacket.class, new UpdateSelectedSlotS2CPacketHandler());
        HANDLERS.put(PlayerPositionLookS2CPacket.class, new PlayerPositionLookS2CPacketHandler());
        HANDLERS.put(ServerMetadataS2CPacket.class, new ServerMetadataS2CPacketHandler());
        HANDLERS.put(EntitySpawnS2CPacket.class, new EntitySpawnS2CPacketHandler());
        HANDLERS.put(EntitySetHeadYawS2CPacket.class, new EntitySetHeadYawS2CPacketHandler());
        HANDLERS.put(EntityAttributesS2CPacket.class, new EntityAttributesS2CPacketHandler());
        HANDLERS.put(EntityEquipmentUpdateS2CPacket.class, new EntityEquipmentUpdateS2CPacketHandler());
        HANDLERS.put(GameMessageS2CPacket.class, new GameMessageS2CPacketHandler());
        HANDLERS.put(ChunkLoadDistanceS2CPacket.class, new ChunkLoadDistanceS2CPacketHandler());
        HANDLERS.put(EntityVelocityUpdateS2CPacket.class, new EntityVelocityUpdateS2CPacketHandler());
        HANDLERS.put(WorldBorderInitializeS2CPacket.class, new WorldBorderInitializeS2CPacketHandler());
        HANDLERS.put(PlayerSpawnPositionS2CPacket.class, new PlayerSpawnPositionS2CPacketHandler());
        HANDLERS.put(EntityS2CPacket.MoveRelative.class, new MoveRelativeHandler());
        HANDLERS.put(EntityS2CPacket.Rotate.class, new RotateHandler());
        HANDLERS.put(EntityS2CPacket.RotateAndMoveRelative.class, new RotateAndMoveRelativeHandler());
        HANDLERS.put(ChunkDataS2CPacket.class, new ChunkDataS2CPacketHandler());
        HANDLERS.put(LightUpdateS2CPacket.class, new LightUpdateS2CPacketHandler());
        HANDLERS.put(EntitiesDestroyS2CPacket.class, new EntitiesDestroyS2CPacketHandler());
        HANDLERS.put(EntityPositionS2CPacket.class, new EntityPositionS2CPacketHandler());
        HANDLERS.put(BlockUpdateS2CPacket.class, new BlockUpdateS2CPacketHandler());
        HANDLERS.put(PlaySoundS2CPacket.class, new PlaySoundS2CPacketHandler());
        HANDLERS.put(WorldTimeUpdateS2CPacket.class, new WorldTimeUpdateS2CPacketHandler());
        HANDLERS.put(ChunkDeltaUpdateS2CPacket.class, new ChunkDeltaUpdateS2CPacketHandler());
        HANDLERS.put(EntityTrackerUpdateS2CPacket.class, new EntityTrackerUpdateS2CPacketHandler());
        HANDLERS.put(PlayerActionResponseS2CPacket.class, new PlayerActionResponseS2CPacketHandler());
        HANDLERS.put(EntityStatusS2CPacket.class, new EntityStatusS2CPacketHandler());
        HANDLERS.put(EntityDamageS2CPacket.class, new EntityDamageS2CPacketHandler());
        HANDLERS.put(ChunkRenderDistanceCenterS2CPacket.class, new ChunkRenderDistanceCenterS2CPacketHandler());
        HANDLERS.put(ParticleS2CPacket.class, new ParticleS2CPacketHandler());
    }

    public static <T extends Packet<?>> void handlePacket(T packet) {
        if (packet instanceof BundleS2CPacket bundleS2CPacket) {
            bundleS2CPacket.getPackets().forEach(PacketHandler::handlePacket);
            return;
        }

        BasePacketHandler<?> handler = HANDLERS.get(packet.getClass());

        if (handler != null) {
            BasePacketHandler<T> packetHandler = (BasePacketHandler<T>) handler;

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", packetHandler.id());
            jsonObject.addProperty("legacyId", packet.getClass().getSimpleName().replace("S2CPacket", ""));

            try {
                jsonObject.add("data", packetHandler.serialize(packet));
            } catch (Exception e) {
                System.out.println("Error while serializing packet " + packet.getClass().getSimpleName());
                e.printStackTrace();
            }

            PacketLogger.wss.sendAll(jsonObject);
        } else if (PacketLogger.CONFIG.sysOutUnknownPackets()) {
            System.out.println(packet.getClass().getSimpleName());
        }
    }

    public static ArrayList<JsonObject> getRegisteredPacketIds() {
        ArrayList<JsonObject> ids = new ArrayList<>();
        for (BasePacketHandler<?> handler : HANDLERS.values()) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("value", handler.getClass().getSimpleName().replace("S2CPacket", "").replace("Handler", "") + "S2CPacket");
            jsonObject.addProperty("label", handler.id());
            ids.add(jsonObject);
        }
        return ids;
    }
}
