package fr.factionbedrock.newdim.Register;

import fr.factionbedrock.newdim.NewDimension;
import fr.factionbedrock.newdim.Block.NewDimChestBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

@EventBusSubscriber(modid = NewDimension.MODID, bus = Bus.MOD)
public class RegisterBlocks
{
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NewDimension.MODID);
	
	/*NEW TREE*/
	
		//logs
		public static final RegistryObject<RotatedPillarBlock> NEWTREE_LOG = register("newtree_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG)));
	
		//leaves
		public static final RegistryObject<Block> NEWTREE_LEAVES = register("newtree_leaves", () -> new LeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)));
    
		//planks
		public static final RegistryObject<Block> NEWTREE_PLANKS = register("newtree_planks", () -> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
	
    /*---------*/
		
		//public static final RegistryObject<ChestBlock> NEWCHEST = register("newchest", () -> new NewDimChestBlock(AbstractBlock.Properties.from(Blocks.CHEST)));
		public static final RegistryObject<ChestBlock> NEWCHEST = register("newchest", () -> new NewDimChestBlock(Material.ROCK,10f,10f,SoundType.STONE,0,ToolType.PICKAXE));
		
	private static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item)
	{
        RegistryObject<T> register = BLOCKS.register(name, block);
        RegisterItems.ITEMS.register(name, item.apply(register));
        return register;
    }

    private static <B extends Block> RegistryObject<B> register(String name, Supplier<? extends Block> block)
    {
        return (RegistryObject<B>)baseRegister(name, block, RegisterBlocks::registerBlockItem);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block)
    {
        return () -> new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties().group(RegisterItemGroup.GROUP));
    }
}
