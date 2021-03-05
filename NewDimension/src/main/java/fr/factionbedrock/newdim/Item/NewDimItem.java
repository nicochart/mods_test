package fr.factionbedrock.newdim.Item;

import fr.factionbedrock.newdim.Register.RegisterItemGroup;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;

public class NewDimItem extends Item
{
	public NewDimItem() {
        super(new Properties()
                .group(RegisterItemGroup.GROUP)
        );
    }

    public NewDimItem(Rarity rarity) {
        super(new Properties()
                .group(RegisterItemGroup.GROUP)
                .rarity(rarity)
        );
    }

    public NewDimItem(Food food) {
        super(new Properties()
                .food(food)
                .group(RegisterItemGroup.GROUP));
    }
}
