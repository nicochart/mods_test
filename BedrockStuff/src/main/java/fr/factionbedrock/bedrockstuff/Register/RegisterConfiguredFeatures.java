package fr.factionbedrock.bedrockstuff.Register;

import com.google.common.collect.ImmutableList;
import fr.factionbedrock.bedrockstuff.BedrockStuff;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class RegisterConfiguredFeatures
{
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, BedrockStuff.MODID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> bedrockOre = CONFIGURED_FEATURES.register("bedrock_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, RegisterBlocks.bedrockOre.get().defaultBlockState())), 10)));
    //see net.minecraft.data.worldgen.features.OreFeatures to see OreConfiguration() parameters examples
}
