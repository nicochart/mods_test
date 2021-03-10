package fr.factionbedrock.newdim.World;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Attributes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class NewDimBiomeProvider extends NewNetherBiomeProvider {

    public static final MapCodec<NewNetherBiomeProvider> PACKET_CODEC = RecordCodecBuilder.mapCodec(
            (instance) -> instance.group(
                    Codec.LONG.fieldOf("seed")
                            .orElseGet(NewDimSeed::getSeed)
                            .forGetter((netherProvider) -> netherProvider.seed),
                            RecordCodecBuilder.<Pair<Biome.Attributes, Supplier<Biome>>>create((biomeAttributes) -> {
                   	         return biomeAttributes.group(Biome.Attributes.CODEC.fieldOf("parameters").forGetter(Pair::getFirst), Biome.BIOME_CODEC.fieldOf("biome").forGetter(Pair::getSecond)).apply(biomeAttributes, Pair::of);
                   	      })
                            .listOf().fieldOf("biomes")
                            .forGetter((netherProvider) -> netherProvider.biomeAttributes),
                    NewNetherBiomeProvider.Noise.CODEC.fieldOf("temperature_noise")
                            .forGetter((netherProvider) -> netherProvider.temperatureNoise),
                    NewNetherBiomeProvider.Noise.CODEC.fieldOf("humidity_noise")
                            .forGetter((netherProvider) -> netherProvider.humidityNoise),
                    NewNetherBiomeProvider.Noise.CODEC.fieldOf("altitude_noise")
                            .forGetter((netherProvider) -> netherProvider.altitudeNoise),
                    NewNetherBiomeProvider.Noise.CODEC.fieldOf("weirdness_noise")
                            .forGetter((netherProvider) -> netherProvider.weirdnessNoise))
                    .apply(instance, NewNetherBiomeProvider::new));

    public static final Codec<NewNetherBiomeProvider> CODEC = NewNetherBiomeProvider.CODEC;

	private List<Pair<Attributes, Supplier<Biome>>> parameters;
	private Optional<Pair<Registry<Biome>, Preset>> preset;
	private NewDimBiomeProvider(long seed, List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes, Optional<Pair<Registry<Biome>, NewNetherBiomeProvider.Preset>> netherProviderPreset)
    {
        super(seed, biomeAttributes, netherProviderPreset);
    }

    protected Codec<? extends BiomeProvider> codec()
    {
        return CODEC;
    }

    @OnlyIn(Dist.CLIENT)
    public BiomeProvider withSeed(long seed) {
        return new NewDimBiomeProvider(seed, this.parameters, this.preset);
    }
}
