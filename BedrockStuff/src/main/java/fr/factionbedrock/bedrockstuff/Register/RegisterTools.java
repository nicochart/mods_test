package fr.factionbedrock.bedrockstuff.Register;

import fr.factionbedrock.bedrockstuff.BedrockStuff;
import fr.factionbedrock.bedrockstuff.Basis.BasisToolMaterial;
import fr.factionbedrock.bedrockstuff.Tools.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = BedrockStuff.MODID, bus = Bus.MOD)
public class RegisterTools
{
	 public static final Sword bedrockSword = new Sword(BasisToolMaterial.bedrock, new Item.Properties().group(ItemGroup.COMBAT));
	 public static final Hoe bedrockHoe = new Hoe(BasisToolMaterial.bedrock,-3, 0.0F, new Item.Properties().group(ItemGroup.TOOLS));
	 public static final Axe bedrockAxe = new Axe(BasisToolMaterial.bedrock, new Item.Properties().group(ItemGroup.TOOLS));
	 public static final Pickaxe bedrockPickaxe = new Pickaxe(BasisToolMaterial.bedrock, new Item.Properties().group(ItemGroup.TOOLS));
	 public static final Shovel bedrockShovel = new Shovel(BasisToolMaterial.bedrock, new Item.Properties().group(ItemGroup.TOOLS));
	 
	 @SubscribeEvent
	 public static void register(Register<Item> event)
	 {
	        IForgeRegistry<Item> registry = event.getRegistry();
	        
	        bedrockSword.setRegistryName(BedrockStuff.MODID, "bedrock_sword");
	        bedrockHoe.setRegistryName(BedrockStuff.MODID, "bedrock_hoe");
	        bedrockAxe.setRegistryName(BedrockStuff.MODID, "bedrock_axe");
	        bedrockPickaxe.setRegistryName(BedrockStuff.MODID, "bedrock_pickaxe");
	        bedrockShovel.setRegistryName(BedrockStuff.MODID, "bedrock_shovel");
	        registry.registerAll(bedrockSword,bedrockHoe,bedrockAxe,bedrockPickaxe,bedrockShovel);
	 }
}
