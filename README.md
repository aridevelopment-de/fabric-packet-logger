# Fabric Packet Logger

I use this for development at [thejocraft.net](thejocraft.net). Yes, the code may not be the best. If you want to improve it, do it and open a pull request. I mainly use it for debugging purposes, so I don't really care about performance or code style.

## Features

- Web-UI (Default Port 8080)
- Websocket Connection (Default Port 1337)
- InGame config
- TODO: InGame packet history viewer

## Commands

- ``/packetlogger`` is the base command for all subcommands. You should be able to auto-complete them
- ``/packetlogger toggleLogging`` - toggles logging
- ``/packetlogger open`` - opens the web-ui

## Supported packets

As developing serializers for each packet takes a lot of time, not every packet is supported. I will add more packets over time. If you want to help out, feel free to open a pull request.

<!-- console.log(a.map((data) => `<li><code>${data.value} (${data.label})</code></li>`).sort().join("\n")) -->

<details>
    <summary>Supported Packets</summary>
    <ul>
        <li><code>AdvancementUpdateS2CPacket (UpdateAdvancements)</code></li>
        <li><code>BlockUpdateS2CPacket (BlockUpdate)</code></li>
        <li><code>ChunkDataS2CPacket (ChunkDataAndLightUpdate)</code></li>
        <li><code>ChunkDeltaUpdateS2CPacket (UpdateSectionBlocks)</code></li>
        <li><code>ChunkLoadDistanceS2CPacket (SetRenderDistance)</code></li>
        <li><code>ChunkRenderDistanceCenterS2CPacket (SetCenterChunk)</code></li>
        <li><code>CustomPayloadS2CPacket (PluginMessage)</code></li>
        <li><code>DifficultyS2CPacket (ChangeDifficulty)</code></li>
        <li><code>EntitiesDestroyS2CPacket (RemoveEntities)</code></li>
        <li><code>EntityAttributesS2CPacket (UpdateAttributes)</code></li>
        <li><code>EntityDamageS2CPacket (DamageEvent)</code></li>
        <li><code>EntityEquipmentUpdateS2CPacket (SetEquipment)</code></li>
        <li><code>EntityPositionS2CPacket (TeleportEntity)</code></li>
        <li><code>EntitySetHeadYawS2CPacket (SetHeadRotation)</code></li>
        <li><code>EntitySpawnS2CPacket (SpawnEntity)</code></li>
        <li><code>EntityStatusS2CPacket (SetEntityMetadata)</code></li>
        <li><code>EntityTrackerUpdateS2CPacket (UpdateAttributes?)</code></li>
        <li><code>EntityVelocityUpdateS2CPacket (SetEntityVelocity)</code></li>
        <li><code>ExperienceBarUpdateS2CPacket (SetExperience)</code></li>
        <li><code>ExperienceOrbSpawnS2CPacket (SpawnExperienceOrb)</code></li>
        <li><code>FeaturesS2CPacket (FeatureFlags)</code></li>
        <li><code>GameJoinS2CPacket (LoginPlay)</code></li>
        <li><code>GameMessageS2CPacket (SystemChatMessage)</code></li>
        <li><code>HealthUpdateS2CPacket (SetHealth)</code></li>
        <li><code>InventoryS2CPacket (SetContainerContent)</code></li>
        <li><code>ItemPickupAnimationS2CPacket (PickupItem)</code></li>
        <li><code>KeepAliveS2CPacket (KeepAlive)</code></li>
        <li><code>LightUpdateS2CPacket (LightUpdate)</code></li>
        <li><code>LoginCompressionS2CPacket (SetCompression)</code></li>
        <li><code>LoginSuccessS2CPacket (LoginSuccess)</code></li>
        <li><code>MoveRelativeS2CPacket (UpdateEntityPosition)</code></li>
        <li><code>OpenScreenS2CPacket (OpenScreen)</code></li>
        <li><code>ParticleS2CPacket (Particle)</code></li>
        <li><code>PlaySoundS2CPacket (SoundEffect)</code></li>
        <li><code>PlayerAbilitiesS2CPacket (PlayerAbilities)</code></li>
        <li><code>PlayerActionResponseS2CPacket (PlayerActionResponse?)</code></li>
        <li><code>PlayerListS2CPacket (PlayerInfoUpdate)</code></li>
        <li><code>PlayerPositionLookS2CPacket (SynchronizePlayerPosition)</code></li>
        <li><code>PlayerSpawnPositionS2CPacket (SetDefaultSpawnPosition)</code></li>
        <li><code>QueryPongS2CPacket (PongPlay)</code></li>
        <li><code>QueryResponseS2CPacket (StatusResponse)</code></li>
        <li><code>RotateAndMoveRelativeS2CPacket (UpdateEntityPositionAndRotation)</code></li>
        <li><code>RotateS2CPacket (UpdateEntityRotation)</code></li>
        <li><code>ScreenSlotUpdateS2CPacket (SetContainerSlot)</code></li>
        <li><code>ServerMetadataS2CPacket (ServerData)</code></li>
        <li><code>SimulationDistanceS2CPacket (SetSimulationDistance)</code></li>
        <li><code>SynchronizeRecipesS2CPacket (UpdateRecipes)</code></li>
        <li><code>SynchronizeTagsS2CPacket (UpdateTags)</code></li>
        <li><code>UnloadChunkS2CPacket (UnloadChunk)</code></li>
        <li><code>UnlockRecipesS2CPacket (UpdateRecipeBook)</code></li>
        <li><code>UpdateSelectedSlotS2CPacket (SetHeldItem)</code></li>
        <li><code>WorldBorderInitializeS2CPacket (InitializeWorldBorder)</code></li>
        <li><code>WorldEventS2CPacket (WorldEvent)</code></li>
        <li><code>WorldTimeUpdateS2CPacket (UpdateTime)</code></li>
    </ul>
</details>

## Why do you not use Reflections/... to convert the packet data?

Mainly because I want to learn about packets themselves. Also, I think serializing each packet itself can help in the process of filtering the correct data and thus making it better for developers to understand. It also allows me to provide fallback values / filtering if certain data is missing.

## What are these ids?

The names you see in the web-ui are the names taken from wiki.vg. This provides much faster navigation to the correct packet data. If you see a question mark after the packet, that means I'm not sure if the wiki.vg entry and the packet match.

## Websocket

By default, the web-ui connects to the websocket and receives the packet information via json data. You can also connect to this websocket as well and receive the same data as the web-ui. I'd recommend using [insomnia](https://insomnia.rest/) or [weasel](https://addons.mozilla.org/de/firefox/addon/websocket-weasel/) on firefox.

## Web-UI

I have a different React project locally which contains the code for the web-ui. I will probably port it to a single html file at some point as otherwise a http server is running parallel with minecraft. In the end, it's mainly a tool I developed for my own purpose, and it was never meant to be used by others.

Previews:

[![Preview](https://cdn.discordapp.com/attachments/598256161212596235/1092927191442010142/image.png)](https://cdn.discordapp.com/attachments/598256161212596235/1092927191442010142/image.png)
