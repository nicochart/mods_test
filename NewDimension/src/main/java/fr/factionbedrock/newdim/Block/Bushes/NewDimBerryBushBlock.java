package fr.factionbedrock.newdim.Block.Bushes;

import fr.factionbedrock.newdim.Setup.Registration;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import net.minecraft.block.AbstractBlock;

public class NewDimBerryBushBlock extends NewDimBushBlock
{
	public NewDimBerryBushBlock(AbstractBlock.Properties properties)
	{super(properties);}
	
	@Override
	public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state)
	{
		super.onPlayerDestroy(worldIn, pos, state);
		worldIn.setBlockState(pos, Registration.NEWDIM_BERRY_BUSH_STEM.get().getDefaultState(), 3);
	}
}