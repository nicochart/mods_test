package fr.factionbedrock.bedrockstuff.Register;

import fr.factionbedrock.bedrockstuff.BedrockStuff;
import fr.factionbedrock.bedrockstuff.Armor.ItemArmor;
import fr.factionbedrock.bedrockstuff.Basis.BasisArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = BedrockStuff.MODID, bus = Bus.MOD)
public class RegisterArmor
{
	public static final ItemArmor bedrockHelmet = new ItemArmor(BasisArmorMaterial.bedrock, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT));
    public static final ItemArmor bedrockChestplate = new ItemArmor(BasisArmorMaterial.bedrock, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT));
    public static final ItemArmor bedrockLeggings = new ItemArmor(BasisArmorMaterial.bedrock, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT));
    public static final ItemArmor bedrockBoots = new ItemArmor(BasisArmorMaterial.bedrock, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT));
    
    @SubscribeEvent
	public static void register(Register<Item> event)
	{
	     IForgeRegistry<Item> registry = event.getRegistry();
	        
	     bedrockHelmet.setRegistryName(BedrockStuff.MODID, "bedrock_helmet");
	     bedrockChestplate.setRegistryName(BedrockStuff.MODID, "bedrock_chestplate");
	     bedrockLeggings.setRegistryName(BedrockStuff.MODID, "bedrock_leggings");
	     bedrockBoots.setRegistryName(BedrockStuff.MODID, "bedrock_boots");
	     registry.registerAll(bedrockHelmet,bedrockChestplate,bedrockLeggings,bedrockBoots);
	}
}
