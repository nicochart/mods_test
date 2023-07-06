package fr.factionbedrock.bedrockstuff.Event;

import fr.factionbedrock.bedrockstuff.BedrockStuff;
import fr.factionbedrock.bedrockstuff.Register.RegisterArmor;
import fr.factionbedrock.bedrockstuff.Register.RegisterItems;
import fr.factionbedrock.bedrockstuff.Register.RegisterTools;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = BedrockStuff.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CreativeTabEvent
{
    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event)
    {
        List<Item> itemsToAdd = new ArrayList<Item>();
        if(event.getTabKey() == CreativeModeTabs.COMBAT)
        {
            itemsToAdd.add(RegisterTools.bedrockSword.get());
            itemsToAdd.add(RegisterArmor.bedrockHelmet.get());
            itemsToAdd.add(RegisterArmor.bedrockChestplate.get());
            itemsToAdd.add(RegisterArmor.bedrockLeggings.get());
            itemsToAdd.add(RegisterArmor.bedrockBoots.get());
        }
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES)
        {
            itemsToAdd.add(RegisterTools.bedrockAxe.get());
            itemsToAdd.add(RegisterTools.bedrockHoe.get());
            itemsToAdd.add(RegisterTools.bedrockPickaxe.get());
            itemsToAdd.add(RegisterTools.bedrockShovel.get());
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS)
        {
            itemsToAdd.add(RegisterItems.bedrockIngot.get());
            itemsToAdd.add(RegisterItems.bedrockScrap.get());
            itemsToAdd.add(RegisterItems.bedrockOre.get());
            itemsToAdd.add(RegisterItems.deepslateBedrockOre.get());
        }
        addAllItemsToTab(event, itemsToAdd);
    }

    private static void addAllItemsToTab(BuildCreativeModeTabContentsEvent event, List<Item> itemList)
    {
        for (Item item : itemList)
        {
            event.getEntries().put(item.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}