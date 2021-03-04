package fr.factionbedrock.bedrockstuff.Tools;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ShovelItem;
import net.minecraftforge.common.ToolType;

public class Shovel extends ShovelItem
{
	public Shovel(IItemTier material, Properties properties)
	{
        super(material, 1.5F, -3F, properties.addToolType(ToolType.SHOVEL, material.getHarvestLevel()));
    }
}
