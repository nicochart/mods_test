package fr.factionbedrock.newdim.World;

import com.mojang.serialization.Codec;

import fr.factionbedrock.newdim.Register.RegisterBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NewDimBiomeProvider extends BiomeProvider
{
	public static final Codec<NewDimBiomeProvider> CODEC = RegistryLookupCodec.getLookUpCodec(Registry.BIOME_KEY)
            .xmap(NewDimBiomeProvider::new, NewDimBiomeProvider::getBiomeRegistry).codec();
	
	private final Biome biome;
    private final Registry<Biome> biomeRegistry;
    private static final List<RegistryKey<Biome>> SPAWN = Collections.singletonList(RegisterBiomes.NEWDIMBIOME);

    public NewDimBiomeProvider(Registry<Biome> biomeRegistry)
    {
        super(getBiomes(biomeRegistry));
        
        final Biome NEWDIMBIOME = biomeRegistry.getOrDefault(RegisterBiomes.NEWDIMBIOME.getLocation());
        biomes.add(NEWDIMBIOME);
        final Biome NEWDIM_QUICKSOILOCEAN = biomeRegistry.getOrDefault(RegisterBiomes.NEWDIM_QUICKSOILOCEAN.getLocation());
        biomes.add(NEWDIM_QUICKSOILOCEAN);
        
        this.biomeRegistry = biomeRegistry;
        biome = biomeRegistry.getOrDefault(RegisterBiomes.NEWDIMBIOME.getLocation());
    }

    private static List<Biome> getBiomes(Registry<Biome> registry)
    {
        return SPAWN.stream().map(s -> registry.getOrDefault(s.getLocation())).collect(Collectors.toList());
    }

    public Registry<Biome> getBiomeRegistry() {return biomeRegistry;}

    @Override
    public boolean hasStructure(Structure<?> structure) {return false;}

    @Override
    protected Codec<? extends BiomeProvider> getBiomeProviderCodec() {return CODEC;}

    @Override
    public BiomeProvider getBiomeProvider(long seed) {return this;}

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {return biome;}
}
