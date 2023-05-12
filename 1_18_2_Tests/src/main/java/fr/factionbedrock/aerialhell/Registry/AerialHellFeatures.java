package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Features.DanglingChainFeature;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class AerialHellFeatures
{
    public static class Features
    {
        public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, AerialHell.MODID);

        public static final RegistryObject<Feature<NoneFeatureConfiguration>> DANGLING_CHAIN = FEATURES.register("dangling_chain", () -> new DanglingChainFeature(NoneFeatureConfiguration.CODEC));
    }

    public static class ConfiguredFeatures
    {
        public static final class Configs
        {
            public static final RandomPatchConfiguration STELLAR_GRASS_CONFIG = new RandomPatchConfiguration(32, 8, 4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AerialHellBlocks.STELLAR_GRASS.get()))));
            public static final RandomPatchConfiguration SHADOW_GRASS_CONFIG = new RandomPatchConfiguration(32, 8, 4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AerialHellBlocks.SHADOW_GRASS.get()))));

            public static final TreeConfiguration OAK_PINE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(5, 2, 1),
                    BlockStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState()),
                    new SpruceFoliagePlacer(UniformInt.of(1, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)), //rayon,décalage,hauteur
                    new TwoLayersFeatureSize(3, 0, 2)
            )).ignoreVines().build();
        }

        public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, AerialHell.MODID);

        public static final RegistryObject<ConfiguredFeature<?, ?>> DANGLING_CHAIN = CONFIGURED_FEATURES.register("dangling_chain", () -> new ConfiguredFeature<>(Features.DANGLING_CHAIN.get(), new NoneFeatureConfiguration()));

        public static final RegistryObject<ConfiguredFeature<?, ?>> PATCH_STELLAR_GRASS = CONFIGURED_FEATURES.register("patch_stellar_grass", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.STELLAR_GRASS_CONFIG));
        public static final RegistryObject<ConfiguredFeature<?, ?>> PATCH_SHADOW_GRASS = CONFIGURED_FEATURES.register("patch_shadow_grass", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.SHADOW_GRASS_CONFIG));

        public static final RegistryObject<ConfiguredFeature<?, ?>> OAK_PINE = CONFIGURED_FEATURES.register("oak_pine", () -> new ConfiguredFeature<>(Feature.TREE, Configs.OAK_PINE_CONFIG));

        public static final RegistryObject<ConfiguredFeature<?, ?>> OAK_PINE_VEGETATION = CONFIGURED_FEATURES.register("oak_pine_vegetation", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacedFeatures.OAK_PINE_CHECKED.getHolder().get(), 0.5F)), PlacedFeatures.OAK_PINE_CHECKED.getHolder().get())));
    }

    public static class PlacedFeatures
    {
        public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, AerialHell.MODID);

        public static final RegistryObject<PlacedFeature> DANGLING_CHAIN_RARE = PLACED_FEATURES.register("dangling_chain_rare", () -> new PlacedFeature(ConfiguredFeatures.DANGLING_CHAIN.getHolder().get(), List.of(CountPlacement.of(32), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));
        public static final RegistryObject<PlacedFeature> DANGLING_CHAIN = PLACED_FEATURES.register("dangling_chain", () -> new PlacedFeature(ConfiguredFeatures.DANGLING_CHAIN.getHolder().get(), List.of(CountPlacement.of(256), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));

        public static final RegistryObject<PlacedFeature> PATCH_STELLAR_GRASS = PLACED_FEATURES.register("patch_stellar_grass", () -> new PlacedFeature(ConfiguredFeatures.PATCH_STELLAR_GRASS.getHolder().get(), List.of(CountPlacement.of(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
        public static final RegistryObject<PlacedFeature> PATCH_SHADOW_GRASS = PLACED_FEATURES.register("patch_shadow_grass", () -> new PlacedFeature(ConfiguredFeatures.PATCH_SHADOW_GRASS.getHolder().get(), List.of(CountPlacement.of(64), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));

        public static final RegistryObject<PlacedFeature> OAK_PINE_CHECKED = PLACED_FEATURES.register("oak_pine_checked", () -> new PlacedFeature(ConfiguredFeatures.OAK_PINE.getHolder().get(), List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING))));

        public static final RegistryObject<PlacedFeature> OAK_PINE_VEGETATION = PLACED_FEATURES.register("oak_pine_vegetation", () -> new PlacedFeature(ConfiguredFeatures.OAK_PINE_VEGETATION.getHolder().get(), VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2))));
    }
}
