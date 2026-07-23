package com.example.hollowknightmod;

import com.example.hollowknightmod.entity.CrawtidEntity;
import com.example.hollowknightmod.entity.TiktikEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = HollowKnightMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModEntityEvents {
    private ModEntityEvents() {
    }

    @SubscribeEvent
    public static void createAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.CRAWTID.get(), CrawtidEntity.createAttributes().build());
        event.put(ModEntityTypes.TIKTIK.get(), TiktikEntity.createAttributes().build());
    }

    public static void registerSpawnPlacements() {
        EntitySpawnPlacementRegistry.register(
                ModEntityTypes.CRAWTID.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                ModEntityEvents::checkSpawnRules
        );
        EntitySpawnPlacementRegistry.register(
                ModEntityTypes.TIKTIK.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                ModEntityEvents::checkSpawnRules
        );
    }

    private static <T extends MonsterEntity> boolean checkSpawnRules(
            EntityType<T> entityType,
            IServerWorld world,
            SpawnReason spawnReason,
            BlockPos position,
            Random random
    ) {
        if (!World.OVERWORLD.equals(world.getLevel().dimension())
                || !MonsterEntity.checkMonsterSpawnRules(entityType, world, spawnReason, position, random)) {
            return false;
        }

        int surfaceY = world.getLevel().getHeight(
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                position.getX(),
                position.getZ()
        );
        boolean isUnderground = position.getY() < surfaceY - 1;
        boolean isForestAtNight = world.getBiome(position).getBiomeCategory() == Biome.Category.FOREST
                && world.getLevel().isNight();

        return isUnderground || isForestAtNight;
    }
}
