package de.ari24.packetlogger.packets;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.PacketLogger;
import de.ari24.packetlogger.packets.clientbound.*;
import de.ari24.packetlogger.packets.serverbound.*;
import de.ari24.packetlogger.web.handlers.WSSPacket;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.NetworkState;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.handshake.HandshakeC2SPacket;
import net.minecraft.network.packet.c2s.login.LoginHelloC2SPacket;
import net.minecraft.network.packet.c2s.login.LoginKeyC2SPacket;
import net.minecraft.network.packet.c2s.login.LoginQueryResponseC2SPacket;
import net.minecraft.network.packet.c2s.play.*;
import net.minecraft.network.packet.c2s.query.QueryPingC2SPacket;
import net.minecraft.network.packet.c2s.query.QueryRequestC2SPacket;
import net.minecraft.network.packet.s2c.login.*;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.network.packet.s2c.query.QueryPongS2CPacket;
import net.minecraft.network.packet.s2c.query.QueryResponseS2CPacket;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import static de.ari24.packetlogger.PacketLogger.PACKET_TICKER;

public class PacketHandler {
    @Getter
    private static final Map<Class<? extends Packet<?>>, BasePacketHandler<?>> HANDLERS = new HashMap<>();

    @Getter
    private static final List<PacketData> packetData = new ArrayList<>();
    private static final Queue<SerializedPacketData> readyForSending = new ConcurrentLinkedQueue<>();

    private static Timer timer;

    static {
        // Clientbound
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
        HANDLERS.put(EndCombatS2CPacket.class, new EndCombatS2CPacketHandler());
        HANDLERS.put(EnterCombatS2CPacket.class, new EnterCombatS2CPacketHandler());
        HANDLERS.put(CooldownUpdateS2CPacket.class, new CooldownUpdateS2CPacketHandler());
        HANDLERS.put(LoginHelloS2CPacket.class, new LoginHelloS2CPacketHandler());
        HANDLERS.put(LoginDisconnectS2CPacket.class, new LoginDisconnectS2CPacketHandler());
        HANDLERS.put(BossBarS2CPacket.class, new BossBarS2CPacketHandler());
        HANDLERS.put(ExplosionS2CPacket.class, new ExplosionS2CPacketHandler());
        HANDLERS.put(StopSoundS2CPacket.class, new StopSoundS2CPacketHandler());
        HANDLERS.put(CommandSuggestionsS2CPacket.class, new CommandSuggestionsS2CPacketHandler());
        HANDLERS.put(CommandTreeS2CPacket.class, new CommandTreeS2CPacketHandler());
        HANDLERS.put(ScoreboardDisplayS2CPacket.class, new ScoreboardDisplayS2CPacketHandler());
        HANDLERS.put(EntityAttachS2CPacket.class, new EntityAttachS2CPacketHandler());
        HANDLERS.put(WorldBorderCenterChangedS2CPacket.class, new WorldBorderCenterChangedS2CPacketHandler());
        HANDLERS.put(WorldBorderSizeChangedS2CPacket.class, new WorldBorderSizeChangedS2CPacketHandler());
        HANDLERS.put(SubtitleS2CPacket.class, new SubtitleS2CPacketHandler());
        HANDLERS.put(ClearTitleS2CPacket.class, new ClearTitleS2CPacketHandler());
        HANDLERS.put(PlaySoundFromEntityS2CPacket.class, new PlaySoundFromEntityS2CPacketHandler());
        HANDLERS.put(PlayerListHeaderS2CPacket.class, new PlayerListHeaderS2CPacketHandler());
        HANDLERS.put(ChatSuggestionsS2CPacket.class, new ChatSuggestionsS2CPacketHandler());
        HANDLERS.put(ChatMessageS2CPacket.class, new ChatMessageS2CPacketHandler());
        HANDLERS.put(TitleS2CPacket.class, new TitleS2CPacketHandler());
        HANDLERS.put(TitleFadeS2CPacket.class, new TitleFadeS2CPacketHandler());
        HANDLERS.put(DeathMessageS2CPacket.class, new DeathMessageS2CPacketHandler());
        HANDLERS.put(ProfilelessChatMessageS2CPacket.class, new ProfilelessChatMessageS2CPacketHandler());
        HANDLERS.put(DisconnectS2CPacket.class, new DisconnectS2CPacketHandler());
        HANDLERS.put(SetTradeOffersS2CPacket.class, new SetTradeOffersS2CPacketHandler());
        HANDLERS.put(LookAtS2CPacket.class, new LookAtS2CPacketHandler());
        HANDLERS.put(PlayerRemoveS2CPacket.class, new PlayerRemoveS2CPacketHandler());
        HANDLERS.put(WorldBorderWarningBlocksChangedS2CPacket.class, new WorldBorderWarningBlocksChangedS2CPacketHandler());
        HANDLERS.put(WorldBorderWarningTimeChangedS2CPacket.class, new WorldBorderWarningTimeChangedS2CPacketHandler());
        HANDLERS.put(RemoveMessageS2CPacket.class, new RemoveMessageS2CPacketHandler());
        HANDLERS.put(ScoreboardPlayerUpdateS2CPacket.class, new ScoreboardPlayerUpdateS2CPacketHandler());
        HANDLERS.put(ScoreboardObjectiveUpdateS2CPacket.class, new ScoreboardObjectiveUpdateS2CPacketHandler());
        HANDLERS.put(TeamS2CPacket.class, new TeamS2CPacketHandler());
        HANDLERS.put(StatisticsS2CPacket.class, new StatisticsS2CPacketHandler());
        HANDLERS.put(BlockBreakingProgressS2CPacket.class, new BlockBreakingProgressS2CPacketHandler());
        HANDLERS.put(PlayerSpawnS2CPacket.class, new PlayerSpawnS2CPacketHandler());
        HANDLERS.put(WorldBorderInterpolateSizeS2CPacket.class, new WorldBorderInterpolateSizeS2CPacketHandler());
        HANDLERS.put(SetCameraEntityS2CPacket.class, new SetCameraEntityS2CPacketHandler());
        HANDLERS.put(SelectAdvancementTabS2CPacket.class, new SelectAdvancementTabS2CPacketHandler());
        HANDLERS.put(ResourcePackSendS2CPacket.class, new ResourcePackSendS2CPacketHandler());
        HANDLERS.put(ScreenHandlerPropertyUpdateS2CPacket.class, new ScreenHandlerPropertyUpdateS2CPacketHandler());
        HANDLERS.put(NbtQueryResponseS2CPacket.class, new NbtQueryResponseS2CPacketHandler());
        HANDLERS.put(OverlayMessageS2CPacket.class, new OverlayMessageS2CPacketHandler());
        HANDLERS.put(VehicleMoveS2CPacket.class, new VehicleMoveS2CPacketHandler());
        HANDLERS.put(OpenHorseScreenS2CPacket.class, new OpenHorseScreenS2CPacketHandler());
        HANDLERS.put(CraftFailedResponseS2CPacket.class, new CraftFailedResponseS2CPacketHandler());
        HANDLERS.put(OpenWrittenBookS2CPacket.class, new OpenWrittenBookS2CPacketHandler());
        HANDLERS.put(SignEditorOpenS2CPacket.class, new SignEditorOpenS2CPacketHandler());
        HANDLERS.put(PlayPingS2CPacket.class, new PlayPingS2CPacketHandler());
        // Java why? ;( HANDLERS.put(BundleSplitterPacket.class, new BundleSplitterPacketHandler());

        // Serverbound
        HANDLERS.put(QueryRequestC2SPacket.class, new QueryRequestC2SPacketHandler());
        HANDLERS.put(QueryPingC2SPacket.class, new QueryPingC2SPacketHandler());
        HANDLERS.put(HandshakeC2SPacket.class, new HandshakeC2SPacketHandler());
        HANDLERS.put(LoginHelloC2SPacket.class, new LoginHelloC2SPacketHandler());
        HANDLERS.put(LoginKeyC2SPacket.class, new LoginKeyC2SPacketHandler());
        HANDLERS.put(LoginQueryResponseC2SPacket.class, new LoginQueryResponseC2SPacketHandler());
        HANDLERS.put(TeleportConfirmC2SPacket.class, new TeleportConfirmC2SPacketHandler());
        HANDLERS.put(ChatMessageC2SPacket.class, new ChatMessageC2SPacketHandler());
        HANDLERS.put(ClientStatusC2SPacket.class, new ClientStatusC2SPacketHandler());
        HANDLERS.put(MessageAcknowledgmentC2SPacket.class, new MessageAcknowledgmentC2SPacketHandler());
        HANDLERS.put(QueryBlockNbtC2SPacket.class, new QueryBlockNbtC2SPacketHandler());
        HANDLERS.put(ButtonClickC2SPacket.class, new ButtonClickC2SPacketHandler());
        HANDLERS.put(ClickSlotC2SPacket.class, new ClickSlotC2SPacketHandler());
        HANDLERS.put(BookUpdateC2SPacket.class, new BookUpdateC2SPacketHandler());
        HANDLERS.put(QueryEntityNbtC2SPacket.class, new QueryEntityNbtC2SPacketHandler());
        HANDLERS.put(PlayerSessionC2SPacket.class, new PlayerSessionC2SPacketHandler());
        HANDLERS.put(UpdateDifficultyC2SPacket.class, new UpdateDifficultyC2SPacketHandler());
        HANDLERS.put(UpdateDifficultyLockC2SPacket.class, new UpdateDifficultyLockC2SPacketHandler());
        HANDLERS.put(UpdatePlayerAbilitiesC2SPacket.class, new UpdatePlayerAbilitiesC2SPacketHandler());
        HANDLERS.put(ClientCommandC2SPacket.class, new ClientCommandC2SPacketHandler());
        HANDLERS.put(CustomPayloadC2SPacket.class, new CustomPayloadC2SPacketHandler());
        HANDLERS.put(CloseHandledScreenC2SPacket.class, new CloseHandledScreenC2SPacketHandler());
        HANDLERS.put(CommandExecutionC2SPacket.class, new CommandExecutionC2SPacketHandler());
        HANDLERS.put(AdvancementTabC2SPacket.class, new AdvancementTabC2SPacketHandler());
        HANDLERS.put(PlayerActionC2SPacket.class, new PlayerActionC2SPacketHandler());
        HANDLERS.put(CreativeInventoryActionC2SPacket.class, new CreativeInventoryActionC2SPacketHandler());
        HANDLERS.put(UpdateSelectedSlotC2SPacket.class, new UpdateSelectedSlotC2SPacketHandler());
        HANDLERS.put(UpdateJigsawC2SPacket.class, new UpdateJigsawC2SPacketHandler());
        HANDLERS.put(UpdateCommandBlockC2SPacket.class, new UpdateCommandBlockC2SPacketHandler());
        HANDLERS.put(UpdateCommandBlockMinecartC2SPacket.class, new UpdateCommandBlockMinecartC2SPacketHandler());
        HANDLERS.put(UpdateStructureBlockC2SPacket.class, new UpdateStructureBlockC2SPacketHandler());
        HANDLERS.put(UpdateSignC2SPacket.class, new UpdateSignC2SPacketHandler());
        HANDLERS.put(SelectMerchantTradeC2SPacket.class, new SelectMerchantTradeC2SPacketHandler());
        HANDLERS.put(ResourcePackStatusC2SPacket.class, new ResourcePackStatusC2SPacketHandler());
        HANDLERS.put(RecipeBookDataC2SPacket.class, new RecipeBookDataC2SPacketHandler());
        HANDLERS.put(PlayPongC2SPacket.class, new PlayPongC2SPacketHandler());
        HANDLERS.put(PlayerInputC2SPacket.class, new PlayerInputC2SPacketHandler());
        HANDLERS.put(SpectatorTeleportC2SPacket.class, new SpectatorTeleportC2SPacketHandler());
        HANDLERS.put(HandSwingC2SPacket.class, new HandSwingC2SPacketHandler());
        HANDLERS.put(CraftRequestC2SPacket.class, new CraftRequestC2SPacketHandler());
        HANDLERS.put(UpdateBeaconC2SPacket.class, new UpdateBeaconC2SPacketHandler());
        HANDLERS.put(RenameItemC2SPacket.class, new RenameItemC2SPacketHandler());
        HANDLERS.put(KeepAliveC2SPacket.class, new KeepAliveC2SPacketHandler());
        HANDLERS.put(JigsawGeneratingC2SPacket.class, new JigsawGeneratingC2SPacketHandler());
        HANDLERS.put(PlayerInteractEntityC2SPacket.class, new PlayerInteractEntityC2SPacketHandler());
        HANDLERS.put(PickFromInventoryC2SPacket.class, new PickFromInventoryC2SPacketHandler());
        HANDLERS.put(VehicleMoveC2SPacket.class, new VehicleMoveC2SPacketHandler());
        HANDLERS.put(RequestCommandCompletionsC2SPacket.class, new RequestCommandCompletionsC2SPacketHandler());
        HANDLERS.put(ClientSettingsC2SPacket.class, new ClientSettingsC2SPacketHandler());
        HANDLERS.put(PlayerInteractBlockC2SPacket.class, new PlayerInteractBlockC2SPacketHandler());
        HANDLERS.put(PlayerInteractItemC2SPacket.class, new PlayerInteractItemC2SPacketHandler());
        HANDLERS.put(BoatPaddleStateC2SPacket.class, new BoatPaddleStateC2SPacketHandler());
        HANDLERS.put(RecipeCategoryOptionsC2SPacket.class, new RecipeCategoryOptionsC2SPacketHandler());
        HANDLERS.put(PlayerMoveC2SPacket.Full.class, new FullHandler());
        HANDLERS.put(PlayerMoveC2SPacket.LookAndOnGround.class, new LookAndOnGroundHandler());
        HANDLERS.put(PlayerMoveC2SPacket.PositionAndOnGround.class, new PositionAndOnGroundHandler());
        HANDLERS.put(PlayerMoveC2SPacket.OnGroundOnly.class, new OnGroundOnlyHandler());
    }

    public static void initialize() {
        if (timer != null) {
            timer.cancel();
        }

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (readyForSending.isEmpty()) return;

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id", WSSPacket.MC_PACKET_RECEIVED.ordinal());

                JsonArray jsonArray = new JsonArray();
                int count = 0;

                while (!readyForSending.isEmpty() || count++ <= PacketLogger.CONFIG.maxPacketsPerSecond()) {
                    SerializedPacketData data = readyForSending.poll();
                    if (data == null) continue;
                    jsonArray.add(data.toJson());
                }

                jsonObject.add("data", jsonArray);
                PacketLogger.wss.sendAll(jsonObject);
            }
        }, 0, 1000);
    }

    public static void cleanup() {
        if (timer != null) {
            timer.cancel();
        }
    }

    @SuppressWarnings("unchecked")
    public static void handlePacket(Packet<?> packet, NetworkSide side) {
        PACKET_TICKER.tick();

        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        NetworkState state = Objects.requireNonNull(NetworkState.getPacketHandlerState(packet));
        int packetId = state.getPacketId(side, packet);
        int index = packetData.size();
        long timestamp = System.currentTimeMillis();

        packet.write(buf);

        packetData.add(new PacketData(buf, state, side, packetId, timestamp));
        readyForSending.offer(new SerializedPacketData(packetId, index, timestamp, state.ordinal(), side.ordinal()));
    }

    public static List<JsonObject> retrieveAllPacketDetails() {
        List<JsonObject> array = new ArrayList<>();

        for (int i = 0; i < packetData.size(); i++) {
            PacketData currentPacketData = packetData.get(i);

            JsonObject data = new JsonObject();
            data.addProperty("id", currentPacketData.packetId());
            data.addProperty("timestamp", currentPacketData.timestamp());
            data.addProperty("networkState", currentPacketData.state().ordinal());
            data.addProperty("direction", currentPacketData.side().ordinal());

            JsonElement bodyData = retrievePacketDetails(i);

            if (bodyData != null) {
                data.add("data", bodyData);
            } else {
                data.add("data", new JsonObject());
            }

            array.add(data);
        }

        return array;
    }

    public static <T extends Packet<?>> JsonElement retrievePacketDetails(int index) {
        PacketData data = packetData.get(index);

        if (data == null) return null;

        PacketByteBuf buf = data.buf();
        NetworkState state = data.state();
        NetworkSide side = data.side();
        int packetId = data.packetId();

        T packet = (T) state.getPacketHandler(side, packetId, buf);

        buf.resetReaderIndex();

        if (packet == null) return null;

        BasePacketHandler<T> handler = (BasePacketHandler<T>) HANDLERS.get(packet.getClass());

        if (handler == null) return null;

        return handler.serialize(packet);
    }

    public record SerializedPacketData(int packetId, int index, long timestamp, int networkState, int direction) {
        public JsonArray toJson() {
            JsonArray array = new JsonArray();
            array.add(packetId);
            array.add(timestamp);
            array.add(index);
            array.add(networkState);
            array.add(direction);
            return array;
        }
    }
    public record PacketData(PacketByteBuf buf, NetworkState state, NetworkSide side, int packetId, long timestamp) {
    }
}
