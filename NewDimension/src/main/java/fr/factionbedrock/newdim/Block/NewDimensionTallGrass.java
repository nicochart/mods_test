package fr.factionbedrock.newdim.Block;

import net.minecraft.block.SoundType;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.block.material.Material;

public class NewDimensionTallGrass extends TallGrassBlock
{
	public NewDimensionTallGrass()
	{
        super(
        		Properties.create(Material.TALL_PLANTS)
                .hardnessAndResistance(0f)
                .sound(SoundType.PLANT)
                .doesNotBlockMovement()
        	 );
    }
}
