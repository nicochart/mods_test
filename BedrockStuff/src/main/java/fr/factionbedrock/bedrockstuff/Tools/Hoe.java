package fr.factionbedrock.bedrockstuff.Tools;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;

public class Hoe extends HoeItem
{
	public Hoe(Tier tier, int attackDamage, float attackSpeed, Properties properties)
	{
        super(tier, attackDamage, attackSpeed, properties);
    }
}
