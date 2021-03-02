package fr.factionbedrock.bedrockstuff.Register;

import fr.factionbedrock.bedrockstuff.BedrockStuff;
import fr.factionbedrock.bedrockstuff.Basis.BasisToolMaterial;
import fr.factionbedrock.bedrockstuff.Tools.Sword;
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
	 
	 @SubscribeEvent
	 public static void register(Register<Item> event)
	 {
	        IForgeRegistry<Item> registry = event.getRegistry();
	        
	        bedrockSword.setRegistryName(BedrockStuff.MODID, "bedrock_sword");
	        registry.registerAll(bedrockSword);
	 }
}
