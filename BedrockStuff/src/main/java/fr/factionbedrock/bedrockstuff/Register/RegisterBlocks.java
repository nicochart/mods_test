package fr.factionbedrock.bedrockstuff.Register;

import fr.factionbedrock.bedrockstuff.BedrockStuff;
import fr.factionbedrock.bedrockstuff.Blocks.BedrockOre;
import fr.factionbedrock.bedrockstuff.Item.ItemFromBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;

import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraft.block.SoundType;

@EventBusSubscriber(modid = BedrockStuff.MODID, bus = Bus.MOD)
public class RegisterBlocks
{
	public static final Block bedrockOre = new BedrockOre
			(
			    AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE)
               .hardnessAndResistance(90f, 100f)
               .harvestTool(ToolType.PICKAXE)
               .harvestLevel(4)
               .sound(SoundType.STONE)
               .setRequiresTool()
            );
      
	@SubscribeEvent
    public static void register(Register<Block> event)
	{
        IForgeRegistry<Block> registry = event.getRegistry();
        
        bedrockOre.setRegistryName(BedrockStuff.MODID, "bedrock_ore");
        registry.registerAll(bedrockOre);
	}
	
	@SubscribeEvent
    public static void registerItem(Register<Item> event) 
	{
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(new ItemFromBlock(bedrockOre, new Item.Properties().group(ItemGroup.MATERIALS)));
	}
}
