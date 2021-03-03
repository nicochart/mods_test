package fr.factionbedrock.bedrockstuff.WorldGen;

import fr.factionbedrock.bedrockstuff.Register.RegisterBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

public class GenOverworldOres
{
	public static ConfiguredFeature<?,?> BEDROCK_ORE = OverworldOres
	(
			RegisterBlocks.bedrockOre.getDefaultState(), //Block enregistré à configurer
			3, //Taille de Filon
			0, //Hauteur minimum
			9, //Hauteur maximum
			2 //Chance d'apparition (Nombre de fillons par chunks)
	);
	
	public static ConfiguredFeature<?,?> OverworldOres(BlockState blockState, int oreVeinSize, int minHeight, int maxHeight, int chance)
	{
        return Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, blockState, oreVeinSize)).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight,0,maxHeight))).square().chance(chance);
    }
}
