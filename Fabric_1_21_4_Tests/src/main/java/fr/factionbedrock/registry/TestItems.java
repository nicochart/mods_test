package fr.factionbedrock.registry;

import fr.factionbedrock.FabricTest;
import fr.factionbedrock.item.CounterItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class TestItems
{
    public static final Item COUNTER_ITEM = register(Keys.COUNTER_ITEM.getValue().getPath(), new CounterItem(new Item.Settings().maxCount(1).registryKey(Keys.COUNTER_ITEM)));
    public static final Item EXAMPLE_BLOCK = register(Keys.EXAMPLE_BLOCK.getValue().getPath(), new BlockItem(TestBlocks.EXAMPLE_BLOCK, new Item.Settings().registryKey(Keys.EXAMPLE_BLOCK).useBlockPrefixedTranslationKey()));

    public static class Keys
    {
        public static final RegistryKey<Item> COUNTER_ITEM = createKey("counter_item");
        public static final RegistryKey<Item> EXAMPLE_BLOCK = createKey("example_block");

        private static RegistryKey<Item> createKey(String name)
        {
            return RegistryKey.of(RegistryKeys.ITEM, FabricTest.id(name));
        }
    }

    public static <T extends Item> T register(String name, T item) {return Registry.register(Registries.ITEM, FabricTest.id(name), item);}

    public static void load() {}
}
