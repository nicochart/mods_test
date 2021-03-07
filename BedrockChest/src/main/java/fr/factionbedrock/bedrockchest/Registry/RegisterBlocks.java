package fr.factionbedrock.bedrockchest.Registry;

import fr.factionbedrock.bedrockchest.BedrockChest;
import fr.factionbedrock.bedrockchest.Registry.RegisterTileEntityTypes;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.RegistryObject;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

@EventBusSubscriber(modid = BedrockChest.MODID, bus = Bus.MOD)
public class RegisterBlocks
{
	static AbstractBlock.Properties materialBedrockChest = AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).
            hardnessAndResistance(10.0F, 10.0F).
            sound(SoundType.STONE).
            notSolid();
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BedrockChest.MODID);
	
	public static final RegistryObject<Block> BEDROCK_CHEST = RegisterBlocks.registerBlock("bedrock_chest",
			() -> new ChestBlock(materialBedrockChest, () -> RegisterTileEntityTypes.CHEST.get()) {
				public net.minecraft.tileentity.TileEntity createTileEntity(BlockState state,
						net.minecraft.world.IBlockReader world) {
					return RegisterTileEntityTypes.CHEST.get().create();
				};});
	
	private static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item)
	{
        RegistryObject<T> register = BLOCKS.register(name, block);
        RegisterItems.ITEMS.register(name, item.apply(register));
        return register;
    }
	
	public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends T> blockSupplier)
	{
		return BLOCKS.register(name, blockSupplier);
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
