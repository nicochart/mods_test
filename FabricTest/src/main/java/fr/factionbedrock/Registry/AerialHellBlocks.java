package fr.factionbedrock.Registry;

import fr.factionbedrock.AerialHell;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AerialHellBlocks
{
    public static final Block AERIAL_TREE_PLANKS = register("aerial_tree_planks", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));

    public static <T extends Block> T register(String name, T block) {return Registry.register(Registries.BLOCK, AerialHell.id(name), block);}

    public static void load() {}
}
