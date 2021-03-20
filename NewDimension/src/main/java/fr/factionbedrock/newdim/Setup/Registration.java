package fr.factionbedrock.newdim.Setup;

import fr.factionbedrock.newdim.Block.NewDimChestBlock;
import fr.factionbedrock.newdim.Block.NewDimGrassBlock;
import fr.factionbedrock.newdim.Block.Aercloud.*;
import fr.factionbedrock.newdim.Block.Bushes.*;
import fr.factionbedrock.newdim.Item.NewDimItem;
import fr.factionbedrock.newdim.Register.RegisterBiomes;
import fr.factionbedrock.newdim.World.Features.*;
import fr.factionbedrock.newdim.World.Tree.NewDimBasicTree;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraft.item.BlockItem;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.IFeatureConfig;

import static fr.factionbedrock.newdim.NewDimension.MODID;

import com.google.common.collect.ImmutableSet;

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
	
	public static final RegistryObject<Block> NEWDIM_WHITE_AERCLOUD = BLOCKS.register("newdim_white_aercloud",	() -> new NewDimAercloudBlock(AbstractBlock.Properties.create(Material.ICE).hardnessAndResistance(0.2F).sound(SoundType.CLOTH).harvestTool(ToolType.HOE).notSolid()));
	public static final RegistryObject<Item> NEWDIM_WHITE_AERCLOUD_ITEM = ITEMS.register("newdim_white_aercloud", () -> new BlockItem(NEWDIM_WHITE_AERCLOUD.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
	
	public static final RegistryObject<Block> NEWDIM_QUICKSOIL_AERCLOUD = BLOCKS.register("newdim_quicksoil_aercloud",	() -> new NewDimQuicksoilAercloudBlock(AbstractBlock.Properties.create(Material.ICE).hardnessAndResistance(0.4F).sound(SoundType.SAND).harvestTool(ToolType.SHOVEL).slipperiness(1.03F).notSolid()));
	public static final RegistryObject<Item> NEWDIM_QUICKSOIL_AERCLOUD_ITEM = ITEMS.register("newdim_quicksoil_aercloud", () -> new BlockItem(NEWDIM_QUICKSOIL_AERCLOUD.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
	
	public static final RegistryObject<Block> NEWDIM_BLUE_AERCLOUD = BLOCKS.register("newdim_blue_aercloud",	() -> new NewDimBlueAercloudBlock(AbstractBlock.Properties.from(Registration.NEWDIM_WHITE_AERCLOUD.get())));
	public static final RegistryObject<Item> NEWDIM_BLUE_AERCLOUD_ITEM = ITEMS.register("newdim_blue_aercloud", () -> new BlockItem(NEWDIM_BLUE_AERCLOUD.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
	
	public static final RegistryObject<Block> NEWDIM_GOLDEN_AERCLOUD = BLOCKS.register("newdim_golden_aercloud",	() -> new NewDimAercloudBlock(AbstractBlock.Properties.from(Registration.NEWDIM_WHITE_AERCLOUD.get())));
	public static final RegistryObject<Item> NEWDIM_GOLDEN_AERCLOUD_ITEM = ITEMS.register("newdim_golden_aercloud", () -> new BlockItem(NEWDIM_GOLDEN_AERCLOUD.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
	
	//bushes
	public static final RegistryObject<Block> NEWDIM_BERRY_BUSH = BLOCKS.register("newdim_berry_bush", () -> new NewDimBerryBushBlock(AbstractBlock.Properties.create(Material.PLANTS).hardnessAndResistance(0.2F).sound(SoundType.PLANT).harvestTool(ToolType.HOE).notSolid()
			.setAllowsSpawn((state, reader, pos, entity) -> (entity == EntityType.OCELOT || entity == EntityType.PARROT)).setSuffocates((state, reader, pos) -> false).setBlocksVision((state, reader, pos) -> false)));
	public static final RegistryObject<Item> NEWDIM_BERRY_BUSH_ITEM = ITEMS.register("newdim_berry_bush", () -> new BlockItem(NEWDIM_BERRY_BUSH.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
	
	public static final RegistryObject<Block> NEWDIM_BERRY_BUSH_STEM = BLOCKS.register("newdim_berry_bush_stem", () -> new NewDimBerryBushStemBlock(AbstractBlock.Properties.create(Material.PLANTS).hardnessAndResistance(0.2F).harvestTool(ToolType.AXE).sound(SoundType.PLANT).doesNotBlockMovement()));
	public static final RegistryObject<Item> NEWDIM_BERRY_BUSH_STEM_ITEM = ITEMS.register("newdim_berry_bush_stem", () -> new BlockItem(NEWDIM_BERRY_BUSH_STEM.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
	
	//flowers
	
	public static final RegistryObject<Block> PURPLE_FLOWER = BLOCKS.register("purple_flower", () -> new FlowerBlock(Effects.BLINDNESS, 4, AbstractBlock.Properties.from(Blocks.DANDELION)));
	public static final RegistryObject<Item> PURPLE_FLOWER_ITEM = ITEMS.register("purple_flower", () -> new BlockItem(PURPLE_FLOWER.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
	
	public static final RegistryObject<Block> WHITE_FLOWER = BLOCKS.register("white_flower", () -> new FlowerBlock(Effects.SLOW_FALLING, 12, AbstractBlock.Properties.from(Blocks.DANDELION)));
	public static final RegistryObject<Item> WHITE_FLOWER_ITEM = ITEMS.register("white_flower", () -> new BlockItem(WHITE_FLOWER.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
	
	
	//newchest
	public static final RegistryObject<ChestBlock> NEWTREE_CHEST = BLOCKS.register("newtree_chest", () -> new NewDimChestBlock(Material.WOOD,10f,10f,SoundType.WOOD,0,ToolType.AXE));
	public static final RegistryObject<Item> NEWTREE_CHEST_ITEM = ITEMS.register("newtree_chest", () -> new BlockItem(NEWTREE_CHEST.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
	
	public static final RegistryObject<Item> BEDROCK_INGOT = ITEMS.register("bedrock_ingot", NewDimItem::new);
	
	
	//features
	public static final RegistryObject<Feature<NoFeatureConfig>> NEWDIM_QUICKSOIL_FEATURE = FEATURES.register("newdim_quicksoil", () -> new NewDimQuicksoilFeature(NoFeatureConfig.field_236558_a_));
	
	public static final RegistryObject<Feature<NoFeatureConfig>> NEWDIM_QUICKSOIL_AERCLOUD_FEATURE = FEATURES.register("newdim_quicksoil_aercloud", () -> new NewDimQuicksoilAercloudFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> NEWDIM_WHITE_AERCLOUD_FEATURE = FEATURES.register("white_aercloud", () -> new NewDimWhiteAercloudFeature(NoFeatureConfig.field_236558_a_));	 
	public static final RegistryObject<Feature<NoFeatureConfig>> NEWDIM_BLUE_AERCLOUD_FEATURE = FEATURES.register("blue_aercloud", () -> new NewDimBlueAercloudFeature(NoFeatureConfig.field_236558_a_));	 
	public static final RegistryObject<Feature<NoFeatureConfig>> NEWDIM_GOLDEN_AERCLOUD_FEATURE = FEATURES.register("golden_aercloud", () -> new NewDimGoldenAercloudFeature(NoFeatureConfig.field_236558_a_));
	 
	public static final RegistryObject<Feature<NoFeatureConfig>> NEWDIM_FLOATING_BUSH_FEATURE = FEATURES.register("newdim_floating_bush", () -> new NewDimFloatingBushFeature(NoFeatureConfig.field_236558_a_));
	 
	public static void registerConfiguredFeatures()
	{
		 register("newdim_quicksoil_feature", NEWDIM_QUICKSOIL_FEATURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(256).square().func_242731_b(20)); //func_242731_b(10)=count(10)
		 register("newdim_quicksoil_aecloud_feature", NEWDIM_QUICKSOIL_AERCLOUD_FEATURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(256).square().func_242731_b(20));
		 register("newdim_white_aercloud_feature", NEWDIM_WHITE_AERCLOUD_FEATURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(128).square().chance(5));
		 register("newdim_blue_aercloud_feature", NEWDIM_BLUE_AERCLOUD_FEATURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(96).square().chance(5));
		 register("newdim_golden_aercloud_feature", NEWDIM_GOLDEN_AERCLOUD_FEATURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(160).square().chance(5));
		 
		 register("newdim_floating_bush_feature", NEWDIM_FLOATING_BUSH_FEATURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).chance(15));
		 
		 register("newdim_basictree_feature", Feature.TREE.withConfiguration
				    ((new BaseTreeFeatureConfig.Builder
				       (
				          new SimpleBlockStateProvider(NEWTREE_LOG.get().getDefaultState()),
	                      new SimpleBlockStateProvider(NEWTREE_LEAVES.get().getDefaultState()),
	                      new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 3), //rayon,décalage,hauteur		func_242252_a()=fixed()
	                      new StraightTrunkPlacer(4, 2, 0), //hauteur de base, randomizer1, randomizer2
	                      new TwoLayerFeature(1, 0, 1)
	                   )
				     ).setIgnoreVines().build()
				    ).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
				 );
	 
	 /*
	    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT) = placement basique
	    .range(256).square().func_242731_b(1) = semble similaire au placement basique
	  	.range(256).square().func_242731_b(10) = beaucoup beaucoup d'arbres (vraiment beaucoup)
	  	
	 */
	 /*
	    This is the tree generated when a new chunk is loaded
	    The tree that grows from a sapling is defined in the class newdim/World/Tree/NewDimBasicTree
	    DON'T FORGET : - To make the tree generate, newdim_grass need to be in the "dirt" forge tag !
	    				 See resources/data/forge/tags/blocks/dirt.json
	    			   - To avoid leaves from decaying next to logs, newdim_log need to be in the "logs_that_burn" minecraft tag
	    			     See resources/data/minecraft/tags/blocks/logs_that_burn.json
	    			   - By the way, place newdim_leaves in the "leaves" minecraft tag
	    			     See resources/data/minecraft/tags/blocks/leaves.json
	 */
		 
		  register("newdim_classicbiome_flowers", Feature.FLOWER.withConfiguration(
	                (new BlockClusterFeatureConfig.Builder(
	                     (new WeightedBlockStateProvider())
	                     .addWeightedBlockstate(PURPLE_FLOWER.get().getDefaultState(), 1)
	                     .addWeightedBlockstate(WHITE_FLOWER.get().getDefaultState(), 1)
	                     .addWeightedBlockstate(NEWDIM_BERRY_BUSH.get().getDefaultState(), 1), SimpleBlockPlacer.PLACER))
	                        .tries(64).whitelist(ImmutableSet.of(NEWDIM_GRASS.get())).build())
				  			.withPlacement(Features.Placements.VEGETATION_PLACEMENT)
				  			.withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(2));
		  //func_242731_b = count     |     addWeightedBlockstate(blockStateIn, weightIn)
	 }
	
	 private static <FC extends IFeatureConfig> void register(String name, ConfiguredFeature<FC, ?> feature)
	 {
		  Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(MODID, name), feature);
	 }
}