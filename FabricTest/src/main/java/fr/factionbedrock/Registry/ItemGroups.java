package fr.factionbedrock.Registry;

import fr.factionbedrock.AerialHell;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

public class ItemGroups
{
    public static final Text AERIAL_HELL_GROUP_TITLE = Text.translatable("itemGroup." + AerialHell.MOD_ID + ".aerial_hell_group");

    public static final ItemGroup AERIAL_HELL_GROUP = register("aerial_hell_group", FabricItemGroup.builder()
            .displayName(AERIAL_HELL_GROUP_TITLE)
            .icon(AerialHellItems.SKY_STICK::getDefaultStack)
            .entries((context, entries) ->
            {
                entries.add(AerialHellItems.SKY_STICK);
                entries.add(AerialHellItems.AERIAL_TREE_PLANKS);
            })
            .build());

    public static <T extends ItemGroup> T register(String name, T itemGroup) {return Registry.register(Registries.ITEM_GROUP, AerialHell.id(name), itemGroup);}

    public static void load() {}
}
