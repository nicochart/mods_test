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

public class BedrockOre extends Block
{
	public BedrockOre()
	{
        super(Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(100F,100F).sound(SoundType.STONE));
    }

    @Nullable
    @Override
    public ToolType getHarvestTool(BlockState state) {return ToolType.PICKAXE;}

    @Override
    public int getHarvestLevel(BlockState state) {return 4;}

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch)
    {
       if (silktouch == 0) return 0; //xp drop si la pioche n'a pas silktouch
       else return 0; //si la pioche a silktouch: pas d'xp drop
    }
}
