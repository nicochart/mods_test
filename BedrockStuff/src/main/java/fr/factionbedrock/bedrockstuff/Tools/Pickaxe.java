package fr.factionbedrock.bedrockstuff.Tools;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.PickaxeItem;

public class Pickaxe extends PickaxeItem
{
	public Pickaxe(Tier tier, Properties properties)
	{
        super(tier, 1, -2.8F, properties);
    }
}
