package fr.factionbedrock.newdim.Setup;

import fr.factionbedrock.newdim.Block.NewDimChestBlock;
import fr.factionbedrock.newdim.Item.NewDimItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static fr.factionbedrock.newdim.NewDimension.MODID;

public class Registration {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static void init()
    {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    
    /*NEW TREE*/
		//logs
		public static final RegistryObject<RotatedPillarBlock> NEWTREE_LOG = BLOCKS.register("newtree_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG)));
		public static final RegistryObject<Item> NEWTREE_LOG_ITEM = ITEMS.register("newtree_log", () -> new BlockItem(NEWTREE_LOG.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
		//BUG HERE (maybe):  Item tag not found
		
		//leaves
		public static final RegistryObject<Block> NEWTREE_LEAVES = BLOCKS.register("newtree_leaves", () -> new LeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)));
		public static final RegistryObject<Item> NEWTREE_LEAVES_ITEM = ITEMS.register("newtree_leaves", () -> new BlockItem(NEWTREE_LEAVES.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
		//BUG HERE : Item tag not found
		
		//planks
		public static final RegistryObject<Block> NEWTREE_PLANKS = BLOCKS.register("newtree_planks", () -> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
		public static final RegistryObject<Item> NEWTREE_PLANKS_ITEM = ITEMS.register("newtree_planks", () -> new BlockItem(NEWTREE_PLANKS.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
		//BUG HERE : Item tag not found
	/*---------*/
    
	//newchest
	public static final RegistryObject<ChestBlock> NEWTREE_CHEST = BLOCKS.register("newtree_chest", () -> new NewDimChestBlock(Material.WOOD,10f,10f,SoundType.WOOD,0,ToolType.AXE));
	public static final RegistryObject<Item> NEWTREE_CHEST_ITEM = ITEMS.register("newtree_chest", () -> new BlockItem(NEWTREE_CHEST.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
	
	public static final RegistryObject<Item> BEDROCK_INGOT = ITEMS.register("bedrock_ingot", NewDimItem::new);
}