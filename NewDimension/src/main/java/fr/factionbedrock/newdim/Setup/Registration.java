package fr.factionbedrock.newdim.Setup;

import fr.factionbedrock.newdim.Block.NewDimChestBlock;
import fr.factionbedrock.newdim.Block.NewDimGrassBlock;
import fr.factionbedrock.newdim.Item.NewDimItem;
import fr.factionbedrock.newdim.Register.RegisterBiomes;
import fr.factionbedrock.newdim.World.Features.NewDimQuicksoilFeature;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.item.BlockItem;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.IFeatureConfig;

import static fr.factionbedrock.newdim.NewDimension.MODID;

public class Registration {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, MODID);

    public static void init()
    {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegisterBiomes.BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    
    /*NEW TREE*/
		//logs
		public static final RegistryObject<RotatedPillarBlock> NEWTREE_LOG = BLOCKS.register("newtree_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG)));
		public static final RegistryObject<Item> NEWTREE_LOG_ITEM = ITEMS.register("newtree_log", () -> new BlockItem(NEWTREE_LOG.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
		
		//leaves
		public static final RegistryObject<Block> NEWTREE_LEAVES = BLOCKS.register("newtree_leaves", () -> new LeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)));
		public static final RegistryObject<Item> NEWTREE_LEAVES_ITEM = ITEMS.register("newtree_leaves", () -> new BlockItem(NEWTREE_LEAVES.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
		
		//planks
		public static final RegistryObject<Block> NEWTREE_PLANKS = BLOCKS.register("newtree_planks", () -> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
		public static final RegistryObject<Item> NEWTREE_PLANKS_ITEM = ITEMS.register("newtree_planks", () -> new BlockItem(NEWTREE_PLANKS.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
	/*---------*/
    
	public static final RegistryObject<Block> NEWDIMSTONE = BLOCKS.register("newdimstone", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)));
	public static final RegistryObject<Item> NEWDIMSTONE_ITEM = ITEMS.register("newdimstone", () -> new BlockItem(NEWDIMSTONE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
	
	public static final RegistryObject<Block> NEWDIM_GRASS = BLOCKS.register("newdim_grass_block", () -> new NewDimGrassBlock(AbstractBlock.Properties.from(Blocks.GRASS_BLOCK).harvestTool(ToolType.SHOVEL)));
	public static final RegistryObject<Item> NEWDIM_GRASS_ITEM = ITEMS.register("newdim_grass_block", () -> new BlockItem(NEWDIM_GRASS.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
	
	public static final RegistryObject<Block> NEWDIM_DIRT = BLOCKS.register("newdim_dirt", () -> new Block(AbstractBlock.Properties.from(Blocks.DIRT)));
	public static final RegistryObject<Item> NEWDIM_DIRT_ITEM = ITEMS.register("newdim_dirt", () -> new BlockItem(NEWDIM_DIRT.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
	
	public static final RegistryObject<Block> NEWDIM_QUICKSOIL = BLOCKS.register("newdim_quicksoil", () -> new Block(AbstractBlock.Properties.from(Blocks.SAND).harvestTool(ToolType.SHOVEL).slipperiness(1.1F)));
	public static final RegistryObject<Item> NEWDIM_QUICKSOIL_ITEM = ITEMS.register("newdim_quicksoil", () -> new BlockItem(NEWDIM_QUICKSOIL.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
	
	//newchest
	public static final RegistryObject<ChestBlock> NEWTREE_CHEST = BLOCKS.register("newtree_chest", () -> new NewDimChestBlock(Material.WOOD,10f,10f,SoundType.WOOD,0,ToolType.AXE));
	public static final RegistryObject<Item> NEWTREE_CHEST_ITEM = ITEMS.register("newtree_chest", () -> new BlockItem(NEWTREE_CHEST.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
	
	public static final RegistryObject<Item> BEDROCK_INGOT = ITEMS.register("bedrock_ingot", NewDimItem::new);
	
	
	//features
	 public static final RegistryObject<Feature<NoFeatureConfig>> NEWDIM_QUICKSOIL_FEATURE = FEATURES.register("newdim_quicksoil", () -> new NewDimQuicksoilFeature(NoFeatureConfig.field_236558_a_));
	 
	 public static void registerConfiguredFeatures()
	 {
		 register("newdim_quicksoil", NEWDIM_QUICKSOIL_FEATURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(256).square().func_242731_b(20)); //func_242731_b(10)=count(10)
	 }
	
	 private static <FC extends IFeatureConfig> void register(String name, ConfiguredFeature<FC, ?> feature)
	 {
		  Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(MODID, name), feature);
	 }
}