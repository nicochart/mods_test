package fr.factionbedrock.bedrockstuff.Register;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static fr.factionbedrock.bedrockstuff.BedrockStuff.MODID;

public class RegisterCreativeModeTabs
{
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> BEDROCKSTUFF = TABS.register("bedrockstuff", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.bedrockstuff")).icon(RegisterItems.bedrockIngot.get()::getDefaultInstance).build());
}
