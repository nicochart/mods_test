package fr.factionbedrock.bedrockstuff.Blocks;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class OverworldOres extends Block //Any ore added in overworld (Stone sound, xp loot, ..)
{
	public OverworldOres()
	{
        super(Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(3F,3F).sound(SoundType.STONE));
    }

    public OverworldOres(Properties properties)
    {
        super(properties);
    }


    @Nullable
    @Override
    public ToolType getHarvestTool(BlockState state) {return ToolType.PICKAXE;}

    @Override
    public int getHarvestLevel(BlockState state) {return 2;}

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch)
    {
       if (silktouch == 0) return 7;
       else return 0;
    }
}
