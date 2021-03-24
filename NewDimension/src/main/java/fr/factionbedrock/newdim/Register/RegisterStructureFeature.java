package fr.factionbedrock.newdim.Register;

import fr.factionbedrock.newdim.NewDimension;
import fr.factionbedrock.newdim.Setup.Registration;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class RegisterStructureFeature
{
    public static StructureFeature<?, ?> CONFIGURED_SMALL_ANGELIC_TEMPLE_STRUCTURE = Registration.SMALL_ANGELIC_TEMPLE_STRUCTURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);

    public static void registerConfiguredStructures()
    {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(NewDimension.MODID, "configured_small_angelic_temple_structure"), CONFIGURED_SMALL_ANGELIC_TEMPLE_STRUCTURE);

        FlatGenerationSettings.STRUCTURES.put(Registration.SMALL_ANGELIC_TEMPLE_STRUCTURE.get(), CONFIGURED_SMALL_ANGELIC_TEMPLE_STRUCTURE);
    }
}