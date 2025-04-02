package fr.factionbedrock.registry;

import fr.factionbedrock.FabricTest;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class TestTags
{
    public static class Items
    {
        public static final TagKey<Item> COUNTER_ITEMS = tag("counter_items");

        private static TagKey<Item> tag(String name) {return TagKey.of(RegistryKeys.ITEM, FabricTest.id(name));}
    }
}
