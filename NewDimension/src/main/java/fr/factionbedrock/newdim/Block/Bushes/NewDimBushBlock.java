package fr.factionbedrock.newdim.Block.Bushes;

import fr.factionbedrock.newdim.Register.RegisterTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import net.minecraft.block.AbstractBlock;

public class NewDimBushBlock extends BushBlock
{
	//Cette classe défini où peut être placé le Bush
	public NewDimBushBlock(AbstractBlock.Properties properties)
	{
		super(properties);
	}
	
	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		Block block = state.getBlock();
		return block.isIn(RegisterTags.Blocks.NEWDIM_DIRT) || super.isValidGround(state, worldIn, pos);
	}
}