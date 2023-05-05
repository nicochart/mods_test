package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.DeadBushBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;

public class AerialHellPlantBlock extends DeadBushBlock
{
	public AerialHellPlantBlock(Properties builder)
	{
		super(builder);
	}
	
	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos)
	{
		Block block = state.getBlock();
		return (this == AerialHellBlocks.SHADOW_GRASS.get() && block == AerialHellBlocks.SHADOW_GRASS_BLOCK.get()) || (this == AerialHellBlocks.STELLAR_GRASS.get() && block == AerialHellBlocks.STELLAR_GRASS_BLOCK.get());
	}
}
