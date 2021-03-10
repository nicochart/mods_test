package fr.factionbedrock.newdim.Register;

import fr.factionbedrock.newdim.NewDimension;
import fr.factionbedrock.newdim.World.NewDimBiomeProvider;
import fr.factionbedrock.newdim.World.NewDimChunkGenerator;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;


public class RegisterDimensions
{
	public static final RegistryKey<DimensionType> NEW_DIMENSION = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, name("newdim"));
    public static final RegistryKey<World> NEW_WORLD = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, name("newdim"));
    
    private static ResourceLocation name(String name) {
        return new ResourceLocation(NewDimension.MODID, name);
    }

    public static void registerDimensionStuff() {
        Registry.register(Registry.CHUNK_GENERATOR_CODEC, name("chunk_generator"), NewDimChunkGenerator.CODEC);
        Registry.register(Registry.BIOME_PROVIDER_CODEC, name("biome_provider"), NewDimBiomeProvider.CODEC);
    }
}
