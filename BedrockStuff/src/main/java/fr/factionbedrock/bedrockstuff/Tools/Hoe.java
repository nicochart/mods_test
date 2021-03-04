package fr.factionbedrock.bedrockstuff.Tools;

import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;

public class Hoe extends HoeItem
{
	public Hoe(IItemTier material, int attackDamage, float attackSpeed, Properties properties)
	{
        super(material, attackDamage, attackSpeed, properties);
    }
}
