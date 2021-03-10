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

public class NewDimChunkGenerator extends ChunkGenerator
{
	public static final Codec<NewDimChunkGenerator> CODEC = RecordCodecBuilder.create(
            (instance) -> instance.group(
                    BiomeProvider.CODEC.fieldOf("biome_source")
                            .forGetter((chunkGenerator) -> chunkGenerator.biomeSource),
                    Codec.LONG.fieldOf("seed")
                            .orElseGet(NewDimSeed::getSeed)
                            .forGetter((chunkGenerator) -> chunkGenerator.seed),
                    DimensionSettings.CODEC.fieldOf("settings")
                            .forGetter((chunkGenerator) -> chunkGenerator.settings))
                    .apply(instance, instance.stable(NewDimChunkGenerator::new)));
	
	protected final BiomeProvider biomeSource;
	
    /*public NewDimChunkGenerator(BiomeProvider biomeProvider, long seed, Supplier<DimensionSettings> dimensionSettingsSupplier)
    {
        super(biomeProvider, (DimensionStructuresSettings) dimensionSettingsSupplier);
        this.seed=seed;
    }*/
    
    public NewDimChunkGenerator(BiomeProvider BiomeProvider1, BiomeProvider BiomeProvider2, DimensionStructuresSettings settings, long seed)
    {
		super(BiomeProvider1,BiomeProvider2,settings,seed);
		this.biomeSource = null;
    }
    
    //func_230347_a_() == codec()
	@Override
	protected Codec<? extends ChunkGenerator> func_230347_a_() {
		// TODO Auto-generated method stub
		return CODEC;
	}
	//func_230349_a_ == withSeed
	@OnlyIn(Dist.CLIENT)
	@Override
	public ChunkGenerator func_230349_a_(long seed) {
		return null;//new NewDimChunkGenerator(this.biomeSource.func_230349_a_(seed), seed, this.settings);
	}

	@Override
	public void generateSurface(WorldGenRegion p_225551_1_, IChunk p_225551_2_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void func_230352_b_(IWorld p_230352_1_, StructureManager p_230352_2_, IChunk p_230352_3_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getHeight(int x, int z, Type heightmapType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IBlockReader func_230348_a_(int p_230348_1_, int p_230348_2_) {
		// TODO Auto-generated method stub
		return null;
	}
}
