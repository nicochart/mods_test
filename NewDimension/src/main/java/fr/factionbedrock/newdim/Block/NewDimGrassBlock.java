package fr.factionbedrock.newdim.Block;

import fr.factionbedrock.newdim.Register.RegisterTags;
import fr.factionbedrock.newdim.Setup.Registration;
import net.minecraft.block.*;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class NewDimGrassBlock extends GrassBlock
{
	public NewDimGrassBlock(AbstractBlock.Properties properties)
	{
		super(properties);
		this.setDefaultState(this.getDefaultState());
	}
	
	@Override
	public void onPlantGrow(BlockState state, IWorld world, BlockPos pos, BlockPos source)
	{
		if (state.isIn(RegisterTags.Blocks.NEWDIM_DIRT))
		{
			world.setBlockState(pos, state, 2);
		}
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		super.fillStateContainer(builder);
	}
	

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
	{
		if (!isSnowyConditions(state, worldIn, pos))
		{
			if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
			worldIn.setBlockState(pos, Registration.NEWDIM_DIRT.get().getDefaultState());
		}
		else
		{
			if (worldIn.getLight(pos.up()) >= 9)
			{
				BlockState blockstate = this.getDefaultState();
				for(int i = 0; i < 4; ++i)
				{
					BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
					if (worldIn.getBlockState(blockpos).isIn(Registration.NEWDIM_DIRT.get()) && isSnowyAndNotUnderwater(blockstate, worldIn, blockpos))
					{
						worldIn.setBlockState(blockpos, blockstate.with(SNOWY, worldIn.getBlockState(blockpos.up()).isIn(Blocks.SNOW)));
					}
				}
			}
		}
	}
	
	
	/* ---- Functions copied from SpreadableSnowyDirtBlock class ---- */
	
	private static boolean isSnowyConditions(BlockState state, IWorldReader worldReader, BlockPos pos) 
	{
	     BlockPos blockpos = pos.up();
	     BlockState blockstate = worldReader.getBlockState(blockpos);
	     if (blockstate.isIn(Blocks.SNOW) && blockstate.get(SnowBlock.LAYERS) == 1) {return true;}
	     else if (blockstate.getFluidState().getLevel() == 8) {return false;}
	     else
	     {
	        int i = LightEngine.func_215613_a(worldReader, state, pos, blockstate, blockpos, Direction.UP, blockstate.getOpacity(worldReader, blockpos));
	        return i < worldReader.getMaxLightLevel();
	     }
	 }
	
	private static boolean isSnowyAndNotUnderwater(BlockState state, IWorldReader worldReader, BlockPos pos)
	{
	    BlockPos blockpos = pos.up();
	    return isSnowyConditions(state, worldReader, pos) && !worldReader.getFluidState(blockpos).isTagged(FluidTags.WATER);
	}
}