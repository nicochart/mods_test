package fr.factionbedrock.Registry;

import fr.factionbedrock.AerialHell;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AerialHellItems
{
    public static final Item SKY_STICK = register("sky_stick", new Item(new Item.Settings().rarity(AerialHellRarities.LEGENDARY)));
    public static final Item AERIAL_TREE_PLANKS = register("aerial_tree_planks", new BlockItem(AerialHellBlocks.AERIAL_TREE_PLANKS, new Item.Settings()));
    public static final Item AERIAL_TREE_CHEST = register("aerial_tree_chest", new BlockItem(AerialHellBlocks.AERIAL_TREE_CHEST, new Item.Settings().component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT)));
    public static final Item EVIL_COW_SPAWN_EGG = register("evil_cow_spawn_egg", new SpawnEggItem(AerialHellEntityTypes.EVIL_COW, -1, -1, new Item.Settings()));

    public static <T extends Item> T register(String name, T item) {return Registry.register(Registries.ITEM, AerialHell.id(name), item);}

    public static void load() {}
}
