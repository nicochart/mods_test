package fr.factionbedrock.aerialhell.Registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

public class AerialHellItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> STELLAR_GRASS_BLOCK = ITEMS.register("stellar_grass_block", () -> new BlockItem(AerialHellBlocks.STELLAR_GRASS_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> STELLAR_DIRT = ITEMS.register("stellar_dirt", () -> new BlockItem(AerialHellBlocks.STELLAR_DIRT.get(), new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> SHADOW_GRASS_BLOCK = ITEMS.register("shadow_grass_block", () -> new BlockItem(AerialHellBlocks.SHADOW_GRASS_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));

    public static final RegistryObject<Item> STELLAR_GRASS = ITEMS.register("stellar_grass", () -> new BlockItem(AerialHellBlocks.STELLAR_GRASS.get(), new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> SHADOW_GRASS = ITEMS.register("shadow_grass", () -> new BlockItem(AerialHellBlocks.SHADOW_GRASS.get(), new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));

    public static final RegistryObject<Item> SLIPPERY_SAND = ITEMS.register("slippery_sand", () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND.get(), new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));

    public static final RegistryObject<Item> STELLAR_STONE = ITEMS.register("stellar_stone", () -> new BlockItem(AerialHellBlocks.STELLAR_STONE.get(), new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
}
