package fr.factionbedrock.bedrockstuff.Armor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

public class ItemArmor extends ArmorItem
{
	public ItemArmor(IArmorMaterial materialIn, EquipmentSlotType slots, Properties properties)
	{
        super(materialIn, slots, properties);
    }
	
	//On peut d�finir ici des fonctions suppl�mentaires aux armures
}
