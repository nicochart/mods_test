package fr.factionbedrock.newdim.World.Tree;

import java.util.Random;

import javax.annotation.Nullable;

import fr.factionbedrock.newdim.Setup.Registration;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class NewDimBasicTree extends Tree
{
	/* 
	This class is useful only to generate a tree from a sapling.
	The tree generated when a new chunk is loaded is in the class newdim/Setup/Registration
	*/
	
	@Nullable
	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
		return Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder
				(
						new SimpleBlockStateProvider(Registration.NEWTREE_LOG.get().getDefaultState()),
						new SimpleBlockStateProvider(Registration.NEWTREE_LEAVES.get().getDefaultState()),
						new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 3), //func_242252_a(int) = fixed(int)
						new StraightTrunkPlacer(4, 2, 0), new TwoLayerFeature(1, 0, 1)
				)).setIgnoreVines().build());
	}
}
