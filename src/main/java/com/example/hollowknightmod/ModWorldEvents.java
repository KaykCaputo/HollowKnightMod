package com.example.hollowknightmod;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HollowKnightMod.MOD_ID)
public class ModWorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        ModOreGeneration.generateOres(event);

        Biome.Category category = event.getCategory();
        if (category != Biome.Category.NETHER
                && category != Biome.Category.THEEND) {
            event.getSpawns().getSpawner(EntityClassification.MONSTER).add(
                    new MobSpawnInfo.Spawners(ModEntityTypes.CRAWTID.get(), 28, 1, 3)
            );
            event.getSpawns().getSpawner(EntityClassification.MONSTER).add(
                    new MobSpawnInfo.Spawners(ModEntityTypes.TIKTIK.get(), 18, 1, 2)
            );
        }
    }
}
