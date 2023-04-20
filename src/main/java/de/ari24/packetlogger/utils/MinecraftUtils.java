package de.ari24.packetlogger.utils;

import net.minecraft.entity.EntityType;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.NetworkState;
import net.minecraft.network.packet.Packet;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Locale;

public class MinecraftUtils {
    public static final ArrayList<EntityType<?>> PROJECTILES = new ArrayList<>();

    static {
        PROJECTILES.add(EntityType.ARROW);
        PROJECTILES.add(EntityType.SPECTRAL_ARROW);
        PROJECTILES.add(EntityType.TRIDENT);
        PROJECTILES.add(EntityType.SNOWBALL);
        PROJECTILES.add(EntityType.EGG);
        PROJECTILES.add(EntityType.ENDER_PEARL);
        PROJECTILES.add(EntityType.POTION);
        PROJECTILES.add(EntityType.EXPERIENCE_BOTTLE);
        PROJECTILES.add(EntityType.FIREWORK_ROCKET);
        PROJECTILES.add(EntityType.FIREBALL);
        PROJECTILES.add(EntityType.SMALL_FIREBALL);
        PROJECTILES.add(EntityType.DRAGON_FIREBALL);
        PROJECTILES.add(EntityType.WITHER_SKULL);
        PROJECTILES.add(EntityType.SHULKER_BULLET);
        PROJECTILES.add(EntityType.LLAMA_SPIT);
        PROJECTILES.add(EntityType.EYE_OF_ENDER);
        PROJECTILES.add(EntityType.EVOKER_FANGS);
        PROJECTILES.add(EntityType.FISHING_BOBBER);
    }

    public static String getPacketId(Class<? extends Packet<?>> packetClass) {
        NetworkState state = NetworkState.HANDLER_STATE_MAP.get(packetClass);
        NetworkState.PacketHandler<?> packetHandler = state.packetHandlers.get(NetworkSide.CLIENTBOUND);
        int id = packetHandler.getId(packetClass);
        return "0x" + StringUtils.leftPad(Integer.toHexString(id), 2, "0").toUpperCase(Locale.ROOT);
    }
}
