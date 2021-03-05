package fr.factionbedrock.newdim.Block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockCreator extends Block
{
    public BlockCreator(Material material, float hardness, float resist, SoundType sound, int harvestlvl, ToolType tool)
    {
        super(
        		Properties.create(material) //material example : (Material.ROCK, MaterialColor.STONE)
                .hardnessAndResistance(hardness,resist) //example : 10.0f
                .sound(sound) //example : SoundType.STONE
                .harvestLevel(harvestlvl) //1 = cobble ; 2 = iron ; 3 = diamond ; 4 = netherite
                .harvestTool(tool) //example : ToolType.PICKAXE
        	 );
    }
}