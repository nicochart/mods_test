package fr.factionbedrock.bedrockstuff.Tools;

import net.minecraft.item.IItemTier;
import net.minecraft.item.PickaxeItem;
import net.minecraftforge.common.ToolType;

public class Pickaxe extends PickaxeItem
{
	public Pickaxe(IItemTier material, Properties properties)
	{
        super(material, 1, -2.8F, properties.addToolType(ToolType.PICKAXE, material.getHarvestLevel()));
    }
}
