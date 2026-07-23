package com.example.hollowknightmod;

import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class ModOreGeneration {
    private static final Lazy<ConfiguredFeature<?, ?>> PALE_ORE_FEATURE =
            Lazy.of(() -> makeOreFeature(OreType.PALE_ORE));

    public static void generateOres(final BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.Category.NETHER
                || event.getCategory() == Biome.Category.THEEND) {
            return;
        }

        event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                PALE_ORE_FEATURE.get());
    }

    private static ConfiguredFeature<?, ?> makeOreFeature(OreType ore) {
        OreFeatureConfig oreFeatureConfig = getOverworldFeatureConfig(ore);

        ConfiguredPlacement<TopSolidRangeConfig> configuredPlacement = Placement.RANGE.configured(
                new TopSolidRangeConfig(ore.getMinHeight(), ore.getMinHeight(), ore.getMaxHeight()));

        return Feature.ORE.configured(oreFeatureConfig)
                .decorated(configuredPlacement)
                .squared()
                .count(ore.getVeinsPerChunk());
    }

    private static OreFeatureConfig getOverworldFeatureConfig(OreType ore) {
        return new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                ore.getBlock().get().defaultBlockState(), ore.getMaxVeinSize());
    }
}
