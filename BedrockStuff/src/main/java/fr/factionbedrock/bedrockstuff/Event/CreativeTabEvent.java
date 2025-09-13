package fr.factionbedrock.bedrockstuff.Event;

import fr.factionbedrock.bedrockstuff.Register.RegisterCreativeModeTabs;
import fr.factionbedrock.bedrockstuff.Register.RegisterItems;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.ArrayList;
import java.util.List;

public class CreativeTabEvent
{
    public static void buildContents(BuildCreativeModeTabContentsEvent event)
    {
        List<Item> itemsToAdd = new ArrayList<Item>();
        if(event.getTabKey() == CreativeModeTabs.COMBAT)
        {
            itemsToAdd.add(RegisterItems.BEDROCK_SWORD.get());
            itemsToAdd.add(RegisterItems.BEDROCK_HELMET.get());
            itemsToAdd.add(RegisterItems.BEDROCK_CHESTPLATE.get());
            itemsToAdd.add(RegisterItems.BEDROCK_LEGGINGS.get());
            itemsToAdd.add(RegisterItems.BEDROCK_BOOTS.get());
        }
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES)
        {
            itemsToAdd.add(RegisterItems.BEDROCK_AXE.get());
            itemsToAdd.add(RegisterItems.BEDROCK_HOE.get());
            itemsToAdd.add(RegisterItems.BEDROCK_PICKAXE.get());
            itemsToAdd.add(RegisterItems.BEDROCK_SHOVEL.get());
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS)
        {
            itemsToAdd.add(RegisterItems.RAW_BEDROCK.get());
            itemsToAdd.add(RegisterItems.BEDROCK_INGOT.get());
            itemsToAdd.add(RegisterItems.BEDROCK_SCRAP.get());
            itemsToAdd.add(RegisterItems.BEDROCK_UPGRADE_SMITHING_TEMPLATE.get());
        }
        if (event.getTabKey() == RegisterCreativeModeTabs.BEDROCKSTUFF.getKey())
        {
            itemsToAdd.add(RegisterItems.BEDROCK_ORE.get());
            itemsToAdd.add(RegisterItems.DEEPSLATE_BEDROCK_ORE.get());
            itemsToAdd.add(RegisterItems.RAW_BEDROCK_BLOCK.get());
            itemsToAdd.add(RegisterItems.BEDROCK_BLOCK.get());
            itemsToAdd.add(RegisterItems.RAW_BEDROCK.get());
            itemsToAdd.add(RegisterItems.BEDROCK_INGOT.get());
            itemsToAdd.add(RegisterItems.BEDROCK_SCRAP.get());
            itemsToAdd.add(RegisterItems.BEDROCK_UPGRADE_SMITHING_TEMPLATE.get());
            itemsToAdd.add(RegisterItems.BEDROCK_AXE.get());
            itemsToAdd.add(RegisterItems.BEDROCK_HOE.get());
            itemsToAdd.add(RegisterItems.BEDROCK_PICKAXE.get());
            itemsToAdd.add(RegisterItems.BEDROCK_SHOVEL.get());
            itemsToAdd.add(RegisterItems.BEDROCK_SWORD.get());
            itemsToAdd.add(RegisterItems.BEDROCK_HELMET.get());
            itemsToAdd.add(RegisterItems.BEDROCK_CHESTPLATE.get());
            itemsToAdd.add(RegisterItems.BEDROCK_LEGGINGS.get());
            itemsToAdd.add(RegisterItems.BEDROCK_BOOTS.get());
        }
        addAllItemsToTab(event, itemsToAdd);
    }

    private static void addAllItemsToTab(BuildCreativeModeTabContentsEvent event, List<Item> itemList)
    {
        for (Item item : itemList)
        {
            event.accept(item.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}