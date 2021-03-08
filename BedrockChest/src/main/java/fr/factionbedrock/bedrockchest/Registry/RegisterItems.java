package fr.factionbedrock.bedrockchest.Registry;

import fr.factionbedrock.bedrockchest.BedrockChest;
import fr.factionbedrock.bedrockchest.Client.Renderer.BedrockChestItemTileEntityRenderer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = BedrockChest.MODID, bus = Bus.MOD)
public class RegisterItems
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BedrockChest.MODID);
	
    public static final RegistryObject<BlockItem> BEDROCK_CHEST = ITEMS.register
    	(
    			"bedrock_chest",
    			() -> new BlockItem
    			(
    				RegisterBlocks.BEDROCK_CHEST.get(),
    				new Item.Properties()
    					.group(RegisterItemGroup.GROUP)
    					.setISTER(() -> BedrockChestItemTileEntityRenderer::new) /*Rendu de l'item dans les mains*/
    			)
    			{public int getBurnTime(net.minecraft.item.ItemStack itemStack) {return 300;};}
    	);
}