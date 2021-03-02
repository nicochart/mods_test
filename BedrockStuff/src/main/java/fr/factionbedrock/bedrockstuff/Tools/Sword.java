package fr.factionbedrock.bedrockstuff.Tools;

import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

public class Sword extends SwordItem
{
	public Sword(IItemTier material, Properties properties)
	{
        super(material, 3, -2.4F, properties);
    }
}
