package de.ari24.packetlogger.utils;

import net.minecraft.entity.EntityType;

import java.util.ArrayList;

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
}
