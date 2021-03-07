package fr.factionbedrock.bedrockchest.Item;

import fr.factionbedrock.bedrockchest.Registry.RegisterItemGroup;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.item.Item.Properties;

public class BedrockChestItem extends Item
{
	public BedrockChestItem() {
        super(new Properties()
                .group(RegisterItemGroup.GROUP)
        );
    }

    public BedrockChestItem(Rarity rarity) {
        super(new Properties()
                .group(RegisterItemGroup.GROUP)
                .rarity(rarity)
        );
    }

    public BedrockChestItem(Food food) {
        super(new Properties()
                .food(food)
                .group(RegisterItemGroup.GROUP));
    }
}
