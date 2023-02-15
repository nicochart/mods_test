package fr.factionbedrock.bedrockstuff.Register;

import fr.factionbedrock.bedrockstuff.BedrockStuff;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class RegisterPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, BedrockStuff.MODID);

    public static final RegistryObject<PlacedFeature> bedrockOre = PLACED_FEATURES.register("bedrock_ore", () -> new PlacedFeature(RegisterConfiguredFeatures.bedrockOre.getHolder().get(), OrePlacements.commonOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.belowTop(16), VerticalAnchor.belowTop(-16)))));
}
