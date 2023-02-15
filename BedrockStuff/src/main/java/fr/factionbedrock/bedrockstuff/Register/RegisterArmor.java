package fr.factionbedrock.bedrockstuff.Register;

import fr.factionbedrock.bedrockstuff.BedrockStuff;
import fr.factionbedrock.bedrockstuff.Armor.ItemArmor;
import fr.factionbedrock.bedrockstuff.Basis.BasisArmorMaterial;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = BedrockStuff.MODID, bus = Bus.MOD)
public class RegisterArmor
{
	public static final DeferredRegister<Item> ARMOR = DeferredRegister.create(ForgeRegistries.ITEMS, BedrockStuff.MODID);

	public static final RegistryObject<Item> bedrockHelmet = ARMOR.register("bedrock_helmet", () -> new ItemArmor(BasisArmorMaterial.bedrock, EquipmentSlot.HEAD, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
	public static final RegistryObject<Item> bedrockChestplate = ARMOR.register("bedrock_chestplate", () -> new ItemArmor(BasisArmorMaterial.bedrock, EquipmentSlot.CHEST, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
	public static final RegistryObject<Item> bedrockLeggings = ARMOR.register("bedrock_leggings", () -> new ItemArmor(BasisArmorMaterial.bedrock, EquipmentSlot.LEGS, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
	public static final RegistryObject<Item> bedrockBoots = ARMOR.register("bedrock_boots", () -> new ItemArmor(BasisArmorMaterial.bedrock, EquipmentSlot.FEET, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
}
