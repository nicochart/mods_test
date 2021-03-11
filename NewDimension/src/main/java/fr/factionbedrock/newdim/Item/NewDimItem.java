package fr.factionbedrock.newdim.Item;

import fr.factionbedrock.newdim.Setup.ModSetup;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;

public class NewDimItem extends Item
{
	public NewDimItem() {
        super(new Properties()
                .group(ModSetup.ITEM_GROUP)
        );
    }

    public NewDimItem(Rarity rarity) {
        super(new Properties()
                .group(ModSetup.ITEM_GROUP)
                .rarity(rarity)
        );
    }

    public NewDimItem(Food food) {
        super(new Properties()
                .food(food)
                .group(ModSetup.ITEM_GROUP));
    }
}
