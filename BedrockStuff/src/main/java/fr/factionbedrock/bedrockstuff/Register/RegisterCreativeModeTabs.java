package fr.factionbedrock.bedrockstuff.Register;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

import static fr.factionbedrock.bedrockstuff.BedrockStuff.MODID;

public class RegisterCreativeModeTabs
{
    public static final net.neoforged.neoforge.registries.DeferredRegister<CreativeModeTab> TABS = net.neoforged.neoforge.registries.DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BEDROCKSTUFF = createTab("bedrockstuff", () -> RegisterItems.BEDROCK_INGOT.get());

    private static DeferredHolder<CreativeModeTab, CreativeModeTab> createTab(String id, Supplier<Item> iconItem)
    {
        return TABS.register(id, () -> CreativeModeTab.builder().title(Component.translatable("itemGroup."+id)).icon(iconItem.get()::getDefaultInstance).build());
    }
}
