package fr.factionbedrock.bedrockstuff.Register;

import fr.factionbedrock.bedrockstuff.BedrockStuff;
import fr.factionbedrock.bedrockstuff.Item.OreItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = BedrockStuff.MODID, bus = Bus.MOD)
public class RegisterItems
{
	public static final OreItems bedrockScrap = new OreItems(new Item.Properties().group(ItemGroup.MATERIALS));
	public static final OreItems bedrockIngot = new OreItems(new Item.Properties().group(ItemGroup.MATERIALS));
	
	@SubscribeEvent
    public static void register(Register<Item> event)
	{
        IForgeRegistry<Item> registry = event.getRegistry();
        
        bedrockScrap.setRegistryName(BedrockStuff.MODID, "bedrock_scrap");
        bedrockIngot.setRegistryName(BedrockStuff.MODID, "bedrock_ingot");
        registry.registerAll(bedrockScrap,bedrockIngot);
	}
}
