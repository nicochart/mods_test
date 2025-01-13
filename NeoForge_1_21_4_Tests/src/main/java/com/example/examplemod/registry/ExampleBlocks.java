package com.example.examplemod.registry;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.NeighborChangedTestBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ExampleBlocks
{
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ExampleMod.MODID);

    public static ResourceKey<Block> EXAMPLE_BLOCK_RESOURCE_KEY = ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "example_block"));
    public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.register(EXAMPLE_BLOCK_RESOURCE_KEY.location().getPath(), () -> new NeighborChangedTestBlock(BlockBehaviour.Properties.of().setId(EXAMPLE_BLOCK_RESOURCE_KEY)));
}
