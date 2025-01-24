package com.example.examplemod.registry;

import com.example.examplemod.ExampleMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ExampleItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExampleMod.MODID);

    public static ResourceKey<Item> EXAMPLE_BLOCK_ITEM_RESOURCE_KEY = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "example_block"));
    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.register(EXAMPLE_BLOCK_ITEM_RESOURCE_KEY.location().getPath(), () -> new BlockItem(ExampleBlocks.EXAMPLE_BLOCK.get(), new Item.Properties().setId(EXAMPLE_BLOCK_ITEM_RESOURCE_KEY)));
}
