package fr.factionbedrock.Util;

import fr.factionbedrock.Registry.AerialHellBlocks;
import fr.factionbedrock.Registry.Worldgen.AerialHellDimensions;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldHelper
{
    public static void neighborUpdate(World world, BlockPos pos)
    {
        if (world.getDimensionEntry().matchesId(AerialHellDimensions.AERIAL_HELL_DIMENSION.getValue()))
        {
            if (world.getFluidState(pos).isIn(FluidTags.LAVA))
            {
                world.setBlockState(pos, AerialHellBlocks.AERIAL_TREE_PLANKS.getDefaultState());
            }
        }
    }
}
