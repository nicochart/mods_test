package fr.factionbedrock.newdim.World;


import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeRegistry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.MaxMinNoiseMixer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Function3;
import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;


public class NewNetherBiomeProvider extends BiomeProvider
{
	/*Copy of the class NetherBiomeProvider with "public" instead of "private"*/
	private static final NewNetherBiomeProvider.Noise DEFAULT_NOISE = new NewNetherBiomeProvider.Noise(-7, ImmutableList.of(1.0D, 1.0D));
	   public static final MapCodec<NewNetherBiomeProvider> PACKET_CODEC = RecordCodecBuilder.mapCodec((builder) -> {
	      return builder.group(Codec.LONG.fieldOf("seed").forGetter((netherProvider) -> {
	         return netherProvider.seed;
	      }), RecordCodecBuilder.<Pair<Biome.Attributes, Supplier<Biome>>>create((biomeAttributes) -> {
	         return biomeAttributes.group(Biome.Attributes.CODEC.fieldOf("parameters").forGetter(Pair::getFirst), Biome.BIOME_CODEC.fieldOf("biome").forGetter(Pair::getSecond)).apply(biomeAttributes, Pair::of);
	      }).listOf().fieldOf("biomes").forGetter((netherProvider) -> {
	         return netherProvider.biomeAttributes;
	      }), NewNetherBiomeProvider.Noise.CODEC.fieldOf("temperature_noise").forGetter((netherProvider) -> {
	         return netherProvider.temperatureNoise;
	      }), NewNetherBiomeProvider.Noise.CODEC.fieldOf("humidity_noise").forGetter((netherProvider) -> {
	         return netherProvider.humidityNoise;
	      }), NewNetherBiomeProvider.Noise.CODEC.fieldOf("altitude_noise").forGetter((netherProvider) -> {
	         return netherProvider.altitudeNoise;
	      }), NewNetherBiomeProvider.Noise.CODEC.fieldOf("weirdness_noise").forGetter((netherProvider) -> {
	         return netherProvider.weirdnessNoise;
	      })).apply(builder, NewNetherBiomeProvider::new);
	   });
	   public static final Codec<NewNetherBiomeProvider> CODEC = Codec.mapEither(NewNetherBiomeProvider.DefaultBuilder.CODEC, PACKET_CODEC).xmap((either) -> {
	      return either.map(NewNetherBiomeProvider.DefaultBuilder::build, Function.identity());
	   }, (netherProvider) -> {
	      return netherProvider.getDefaultBuilder().map(Either::<NewNetherBiomeProvider.DefaultBuilder, NewNetherBiomeProvider>left).orElseGet(() -> {
	         return Either.right(netherProvider);
	      });
	   }).codec();
	   public final NewNetherBiomeProvider.Noise temperatureNoise;
	   public final NewNetherBiomeProvider.Noise humidityNoise;
	   public final NewNetherBiomeProvider.Noise altitudeNoise;
	   public final NewNetherBiomeProvider.Noise weirdnessNoise;
	   public final MaxMinNoiseMixer temperatureNoiseMixer;
	   public final MaxMinNoiseMixer humidityNoiseMixer;
	   public final MaxMinNoiseMixer altitudeNoiseMixer;
	   public final MaxMinNoiseMixer weirdnessNoiseMixer;
	   public final List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes;
	   public final boolean useHeightForNoise;
	   public final long seed;
	   public final Optional<Pair<Registry<Biome>, NewNetherBiomeProvider.Preset>> netherProviderPreset;

	   public NewNetherBiomeProvider(long seed, List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes, Optional<Pair<Registry<Biome>, NewNetherBiomeProvider.Preset>> netherProviderPreset) {
	      this(seed, biomeAttributes, DEFAULT_NOISE, DEFAULT_NOISE, DEFAULT_NOISE, DEFAULT_NOISE, netherProviderPreset);
	   }

	   public NewNetherBiomeProvider(long seed, List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes, NewNetherBiomeProvider.Noise temperatureNoise, NewNetherBiomeProvider.Noise humidityNoise, NewNetherBiomeProvider.Noise altitudeNoise, NewNetherBiomeProvider.Noise weirdnessNoise) {
	      this(seed, biomeAttributes, temperatureNoise, humidityNoise, altitudeNoise, weirdnessNoise, Optional.empty());
	   }

	   public NewNetherBiomeProvider(long seed, List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes, NewNetherBiomeProvider.Noise temperatureNoise, NewNetherBiomeProvider.Noise humidityNoise, NewNetherBiomeProvider.Noise altitudeNoise, NewNetherBiomeProvider.Noise weirdnessNoise, Optional<Pair<Registry<Biome>, NewNetherBiomeProvider.Preset>> netherProviderPreset) {
	      super(biomeAttributes.stream().map(Pair::getSecond));
	      this.seed = seed;
	      this.netherProviderPreset = netherProviderPreset;
	      this.temperatureNoise = temperatureNoise;
	      this.humidityNoise = humidityNoise;
	      this.altitudeNoise = altitudeNoise;
	      this.weirdnessNoise = weirdnessNoise;
	      this.temperatureNoiseMixer = MaxMinNoiseMixer.func_242930_a(new SharedSeedRandom(seed), temperatureNoise.getNumberOfOctaves(), temperatureNoise.getAmplitudes());
	      this.humidityNoiseMixer = MaxMinNoiseMixer.func_242930_a(new SharedSeedRandom(seed + 1L), humidityNoise.getNumberOfOctaves(), humidityNoise.getAmplitudes());
	      this.altitudeNoiseMixer = MaxMinNoiseMixer.func_242930_a(new SharedSeedRandom(seed + 2L), altitudeNoise.getNumberOfOctaves(), altitudeNoise.getAmplitudes());
	      this.weirdnessNoiseMixer = MaxMinNoiseMixer.func_242930_a(new SharedSeedRandom(seed + 3L), weirdnessNoise.getNumberOfOctaves(), weirdnessNoise.getAmplitudes());
	      this.biomeAttributes = biomeAttributes;
	      this.useHeightForNoise = false;
	   }

	   protected Codec<? extends BiomeProvider> getBiomeProviderCodec() {
	      return CODEC;
	   }

	   @OnlyIn(Dist.CLIENT)
	   public BiomeProvider getBiomeProvider(long seed) {
	      return new NewNetherBiomeProvider(seed, this.biomeAttributes, this.temperatureNoise, this.humidityNoise, this.altitudeNoise, this.weirdnessNoise, this.netherProviderPreset);
	   }

	   private Optional<NewNetherBiomeProvider.DefaultBuilder> getDefaultBuilder() {
	      return this.netherProviderPreset.map((registryPresetPair) -> {
	         return new NewNetherBiomeProvider.DefaultBuilder(registryPresetPair.getSecond(), registryPresetPair.getFirst(), this.seed);
	      });
	   }

	   public Biome getNoiseBiome(int x, int y, int z) {
	      int i = this.useHeightForNoise ? y : 0;
	      Biome.Attributes biome$attributes = new Biome.Attributes((float)this.temperatureNoiseMixer.func_237211_a_((double)x, (double)i, (double)z), (float)this.humidityNoiseMixer.func_237211_a_((double)x, (double)i, (double)z), (float)this.altitudeNoiseMixer.func_237211_a_((double)x, (double)i, (double)z), (float)this.weirdnessNoiseMixer.func_237211_a_((double)x, (double)i, (double)z), 0.0F);
	      return this.biomeAttributes.stream().min(Comparator.comparing((attributeBiomePair) -> {
	         return attributeBiomePair.getFirst().getAttributeDifference(biome$attributes);
	      })).map(Pair::getSecond).map(Supplier::get).orElse(BiomeRegistry.THE_VOID);
	   }

	   public boolean isDefaultPreset(long seed) {
	      return this.seed == seed && this.netherProviderPreset.isPresent() && Objects.equals(this.netherProviderPreset.get().getSecond(), NewNetherBiomeProvider.Preset.DEFAULT_NETHER_PROVIDER_PRESET);
	   }

	   static final class DefaultBuilder {
	      public static final MapCodec<NewNetherBiomeProvider.DefaultBuilder> CODEC = RecordCodecBuilder.mapCodec((builder) -> {
	         return builder.group(ResourceLocation.CODEC.flatXmap((id) -> {
	            return Optional.ofNullable(NewNetherBiomeProvider.Preset.PRESETS.get(id)).map(DataResult::success).orElseGet(() -> {
	               return DataResult.error("Unknown preset: " + id);
	            });
	         }, (preset) -> {
	            return DataResult.success(preset.id);
	         }).fieldOf("preset").stable().forGetter(NewNetherBiomeProvider.DefaultBuilder::getPreset), RegistryLookupCodec.getLookUpCodec(Registry.BIOME_KEY).forGetter(NewNetherBiomeProvider.DefaultBuilder::getLookupRegistry), Codec.LONG.fieldOf("seed").stable().forGetter(NewNetherBiomeProvider.DefaultBuilder::getSeed)).apply(builder, builder.stable(NewNetherBiomeProvider.DefaultBuilder::new));
	      });
	      private final NewNetherBiomeProvider.Preset preset;
	      private final Registry<Biome> lookupRegistry;
	      private final long seed;

	      private DefaultBuilder(NewNetherBiomeProvider.Preset preset, Registry<Biome> lookupRegistry, long seed) {
	         this.preset = preset;
	         this.lookupRegistry = lookupRegistry;
	         this.seed = seed;
	      }

	      public NewNetherBiomeProvider.Preset getPreset() {
	         return this.preset;
	      }

	      public Registry<Biome> getLookupRegistry() {
	         return this.lookupRegistry;
	      }

	      public long getSeed() {
	         return this.seed;
	      }

	      public NewNetherBiomeProvider build() {
	         return this.preset.build(this.lookupRegistry, this.seed);
	      }
	   }

	   static class Noise {
	      private final int numOctaves;
	      private final DoubleList amplitudes;
	      public static final Codec<NewNetherBiomeProvider.Noise> CODEC = RecordCodecBuilder.create((builder) -> {
	         return builder.group(Codec.INT.fieldOf("firstOctave").forGetter(NewNetherBiomeProvider.Noise::getNumberOfOctaves), Codec.DOUBLE.listOf().fieldOf("amplitudes").forGetter(NewNetherBiomeProvider.Noise::getAmplitudes)).apply(builder, NewNetherBiomeProvider.Noise::new);
	      });

	      public Noise(int numOctaves, List<Double> amplitudes) {
	         this.numOctaves = numOctaves;
	         this.amplitudes = new DoubleArrayList(amplitudes);
	      }

	      public int getNumberOfOctaves() {
	         return this.numOctaves;
	      }

	      public DoubleList getAmplitudes() {
	         return this.amplitudes;
	      }
	   }

	   public static class Preset {
	      private static final Map<ResourceLocation, NewNetherBiomeProvider.Preset> PRESETS = Maps.newHashMap();
	      public static final NewNetherBiomeProvider.Preset DEFAULT_NETHER_PROVIDER_PRESET = new NewNetherBiomeProvider.Preset(new ResourceLocation("nether"), (preset, lookupRegistry, seed) -> {
	         return new NewNetherBiomeProvider(seed, ImmutableList.of(Pair.of(new Biome.Attributes(0.0F, 0.0F, 0.0F, 0.0F, 0.0F), () -> {
	            return lookupRegistry.getOrThrow(Biomes.NETHER_WASTES);
	         }), Pair.of(new Biome.Attributes(0.0F, -0.5F, 0.0F, 0.0F, 0.0F), () -> {
	            return lookupRegistry.getOrThrow(Biomes.SOUL_SAND_VALLEY);
	         }), Pair.of(new Biome.Attributes(0.4F, 0.0F, 0.0F, 0.0F, 0.0F), () -> {
	            return lookupRegistry.getOrThrow(Biomes.CRIMSON_FOREST);
	         }), Pair.of(new Biome.Attributes(0.0F, 0.5F, 0.0F, 0.0F, 0.375F), () -> {
	            return lookupRegistry.getOrThrow(Biomes.WARPED_FOREST);
	         }), Pair.of(new Biome.Attributes(-0.5F, 0.0F, 0.0F, 0.0F, 0.175F), () -> {
	            return lookupRegistry.getOrThrow(Biomes.BASALT_DELTAS);
	         })), Optional.of(Pair.of(lookupRegistry, preset)));
	      });
	      private final ResourceLocation id;
	      private final Function3<NewNetherBiomeProvider.Preset, Registry<Biome>, Long, NewNetherBiomeProvider> netherProviderFunction;

	      public Preset(ResourceLocation id, Function3<NewNetherBiomeProvider.Preset, Registry<Biome>, Long, NewNetherBiomeProvider> netherProviderFunction) {
	         this.id = id;
	         this.netherProviderFunction = netherProviderFunction;
	         PRESETS.put(id, this);
	      }

	      public NewNetherBiomeProvider build(Registry<Biome> lookupRegistry, long seed) {
	         return this.netherProviderFunction.apply(this, lookupRegistry, seed);
	      }
	   }
}
