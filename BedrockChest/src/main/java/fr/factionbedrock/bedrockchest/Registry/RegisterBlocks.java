package fr.factionbedrock.bedrockchest.Registry;

import fr.factionbedrock.bedrockchest.BedrockChest;
import net.minecraft.block.Block;
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
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BedrockChest.MODID);
	
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
