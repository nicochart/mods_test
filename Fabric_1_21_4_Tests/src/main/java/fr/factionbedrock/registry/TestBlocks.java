package fr.factionbedrock.registry;

import fr.factionbedrock.FabricTest;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class TestBlocks
{
    public static final Block EXAMPLE_BLOCK = register(Keys.EXAMPLE_BLOCK.getValue().getPath(), new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).registryKey(Keys.EXAMPLE_BLOCK)));

    public static class Keys
    {
        public static final RegistryKey<Block> EXAMPLE_BLOCK = createKey("example_block");

        private static RegistryKey<Block> createKey(String name)
        {
            return RegistryKey.of(RegistryKeys.BLOCK, FabricTest.id(name));
        }
    }

    public static <T extends Block> T register(String name, T block) {return Registry.register(Registries.BLOCK, FabricTest.id(name), block);}

    public static void load() {}
}
