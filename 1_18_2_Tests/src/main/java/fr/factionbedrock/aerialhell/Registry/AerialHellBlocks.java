package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.Block.ShadowGrassBlock;
import fr.factionbedrock.aerialhell.Block.StellarGrassBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

public class AerialHellBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final RegistryObject<Block> STELLAR_GRASS_BLOCK = BLOCKS.register("stellar_grass_block", () -> new StellarGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> SHADOW_GRASS_BLOCK = BLOCKS.register("shadow_grass_block", () -> new ShadowGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> STELLAR_DIRT = BLOCKS.register("stellar_dirt", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));

    public static final RegistryObject<Block> SHADOW_GRASS = BLOCKS.register("shadow_grass", () -> new fr.factionbedrock.aerialhell.Block.Plants.AerialHellPlantBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> STELLAR_GRASS = BLOCKS.register("stellar_grass", () -> new fr.factionbedrock.aerialhell.Block.Plants.AerialHellPlantBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)));

    public static final RegistryObject<Block> SLIPPERY_SAND = BLOCKS.register("slippery_sand", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SAND).friction(1.025F)));

    public static final RegistryObject<Block> STELLAR_STONE = BLOCKS.register("stellar_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
}
