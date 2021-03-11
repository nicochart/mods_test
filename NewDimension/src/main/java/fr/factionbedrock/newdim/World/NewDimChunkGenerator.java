package fr.factionbedrock.newdim.World;

import com.google.common.base.Supplier;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKeyCodec;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.NoiseSettings;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.Heightmap.Type;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class NewDimChunkGenerator extends NewNoiseChunkGenerator
{
	public static final Codec<NewNoiseChunkGenerator> CODEC = RecordCodecBuilder.create((p_236091_0_) -> {
	      return p_236091_0_.group(BiomeProvider.CODEC.fieldOf("biome_source").forGetter((p_236096_0_) -> {
	         return p_236096_0_.getBiomeProvider();
	      }), Codec.LONG.fieldOf("seed").stable().forGetter((p_236093_0_) -> {
	         return p_236093_0_.field_236084_w_;
	      }), DimensionSettings.field_236098_b_.fieldOf("settings").forGetter((p_236090_0_) -> {
	         return p_236090_0_.field_236080_h_;
	      })).apply(p_236091_0_, p_236091_0_.stable(NewNoiseChunkGenerator::new));
	   });
	
    public NewDimChunkGenerator(BiomeProvider biomeProvider, long seed, Supplier<DimensionSettings> dimensionSettingsSupplier) {
        super(biomeProvider, seed, dimensionSettingsSupplier);
    }

    @Override
    protected Codec<? extends ChunkGenerator> func_230347_a_() {
        return CODEC;
    }

    //func_230349_a_ = withSeed
    @OnlyIn(Dist.CLIENT)
    @Override
    public ChunkGenerator func_230349_a_(long p_230349_1_) {
        return new NewNoiseChunkGenerator(this.biomeProvider.getBiomeProvider(p_230349_1_), p_230349_1_, this.field_236080_h_);
     }
}
