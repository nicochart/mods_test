package fr.factionbedrock.bedrockstuff.Tools;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ShovelItem;

public class Shovel extends ShovelItem
{
	public Shovel(Tier tier, Properties properties)
	{
        super(tier, 1.5F, -3F, properties);
    }
}
