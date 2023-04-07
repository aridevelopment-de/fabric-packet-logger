package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.PacketLogger;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.NetworkState;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.login.*;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.network.packet.s2c.query.QueryPongS2CPacket;
import net.minecraft.network.packet.s2c.query.QueryResponseS2CPacket;

import java.util.*;

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
        HANDLERS.put(KeepAliveS2CPacket.class, new KeepAliveS2CPacketHandler());
        HANDLERS.put(UnloadChunkS2CPacket.class, new UnloadChunkS2CPacketHandler());
        HANDLERS.put(PlayerListS2CPacket.class, new PlayerListS2CPacketHandler());
        HANDLERS.put(ScreenHandlerSlotUpdateS2CPacket.class, new ScreenHandlerSlotUpdateS2CPacketHandler());
        HANDLERS.put(ExperienceBarUpdateS2CPacket.class, new ExperienceBarUpdateS2CPacketHandler());
        HANDLERS.put(AdvancementUpdateS2CPacket.class, new AdvancementUpdateS2CPacketHandler());
        HANDLERS.put(ExperienceOrbSpawnS2CPacket.class, new ExperienceOrbSpawnS2CPacketHandler());
        HANDLERS.put(HealthUpdateS2CPacket.class, new HealthUpdateS2CPacketHandler());
        HANDLERS.put(SimulationDistanceS2CPacket.class, new SimulationDistanceS2CPacketHandler());
        HANDLERS.put(ItemPickupAnimationS2CPacket.class, new ItemPickupAnimationS2CPacketHandler());
        HANDLERS.put(OpenScreenS2CPacket.class, new OpenScreenS2CPacketHandler());
        HANDLERS.put(InventoryS2CPacket.class, new InventoryS2CPacketHandler());
        HANDLERS.put(WorldEventS2CPacket.class, new WorldEventS2CPacketHandler());
        HANDLERS.put(UnlockRecipesS2CPacket.class, new UnlockRecipesS2CPacketHandler());
        HANDLERS.put(SynchronizeRecipesS2CPacket.class, new SynchronizeRecipesS2CPacketHandler());
        HANDLERS.put(SynchronizeTagsS2CPacket.class, new SynchronizeTagsS2CPacketHandler());
        HANDLERS.put(EntityPassengersSetS2CPacket.class, new EntityPassengersSetS2CPacketHandler());
        HANDLERS.put(EntityAnimationS2CPacket.class, new EntityAnimationS2CPacketHandler());
        HANDLERS.put(BlockEntityUpdateS2CPacket.class, new BlockEntityUpdateS2CPacketHandler());
        HANDLERS.put(BlockEventS2CPacket.class, new BlockEventS2CPacketHandler());
        HANDLERS.put(MapUpdateS2CPacket.class, new MapUpdateS2CPacketHandler());
        HANDLERS.put(LoginQueryRequestS2CPacket.class, new LoginQueryRequestS2CPacketHandler());
        HANDLERS.put(PlayerRespawnS2CPacket.class, new PlayerRespawnS2CPacketHandler());
        HANDLERS.put(CloseScreenS2CPacket.class, new CloseScreenS2CPacketHandler());
        HANDLERS.put(GameStateChangeS2CPacket.class, new GameStateChangeS2CPacketHandler());
        HANDLERS.put(EntityStatusEffectS2CPacket.class, new EntityStatusEffectS2CPacketHandler());
        HANDLERS.put(RemoveEntityStatusEffectS2CPacket.class, new RemoveEntityStatusEffectS2CPacketHandler());
        HANDLERS.put(DamageTiltS2CPacket.class, new DamageTiltS2CPacketHandler());
        HANDLERS.put(EndCombatS2CPacket.class, new EndCombatS2CPacketHandler());
        HANDLERS.put(EnterCombatS2CPacket.class, new EnterCombatS2CPacketHandler());
        HANDLERS.put(CooldownUpdateS2CPacket.class, new CooldownUpdateS2CPacketHandler());
        HANDLERS.put(LoginHelloS2CPacket.class, new LoginHelloS2CPacketHandler());
        HANDLERS.put(LoginDisconnectS2CPacket.class, new LoginDisconnectS2CPacketHandler());
        // TODO
        /*
        CommandTreeS2CPacket
        CommandSuggestionsS2CPacket
        BossBarS2CPacket
        ExplosionS2CPacket
        StopSoundS2CPacket
        LoginDisconnectS2CPacket

        PositionAndOnGround
        Full
        OnGroundOnly
         */
    }

    @SuppressWarnings("unchecked")
    public static <T extends Packet<?>> void handlePacket(T packet) {
        if (packet instanceof BundleS2CPacket bundleS2CPacket) {
            bundleS2CPacket.getPackets().forEach(PacketHandler::handlePacket);
            return;
        }

        BasePacketHandler<?> handler = HANDLERS.get(packet.getClass());

        if (handler != null) {
            BasePacketHandler<T> packetHandler = (BasePacketHandler<T>) handler;

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", packetHandler.name());

            // TODO: Add mojenk mappings
            jsonObject.addProperty("legacyName", packet.getClass().getSimpleName().replace("S2CPacket", ""));

            NetworkState state = Objects.requireNonNull(NetworkState.getPacketHandlerState(packet));
            int id = state.getPacketId(NetworkSide.CLIENTBOUND, packet);
            jsonObject.addProperty("id", "0x" + Integer.toHexString(id).toUpperCase(Locale.ROOT));

            try {
                jsonObject.add("data", packetHandler.serialize(packet));
            } catch (Exception e) {
                PacketLogger.LOGGER.error("Error while serializing packet " + packet.getClass().getSimpleName());
                PacketLogger.LOGGER.error(e.toString());
            }

            PacketLogger.wss.sendAll(jsonObject);
        } else if (PacketLogger.CONFIG.sysOutUnknownPackets()) {
            PacketLogger.LOGGER.info(packet.getClass().getSimpleName());
        }
    }

    public static Map<Class<? extends Packet<?>>, BasePacketHandler<?>> getHandlers() {
        return HANDLERS;
    }

    public static ArrayList<JsonObject> getRegisteredPacketIds() {
        ArrayList<JsonObject> ids = new ArrayList<>();
        for (BasePacketHandler<?> handler : HANDLERS.values()) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("value", handler.getClass().getSimpleName().replace("S2CPacket", "").replace("Handler", "") + "S2CPacket");
            jsonObject.addProperty("label", handler.name());
            ids.add(jsonObject);
        }
        return ids;
    }

    public static Map<String, JsonObject> getPacketDescriptions() {
        Map<String, JsonObject> descriptions = new HashMap<>();

        for (BasePacketHandler<?> handler : HANDLERS.values()) {
            descriptions.put(handler.name(), handler.description());
        }

        return descriptions;
    }
}
