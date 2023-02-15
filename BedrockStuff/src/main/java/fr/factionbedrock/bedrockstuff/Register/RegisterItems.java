package fr.factionbedrock.bedrockstuff.Register;

import fr.factionbedrock.bedrockstuff.BedrockStuff;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = BedrockStuff.MODID, bus = Bus.MOD)
public class RegisterItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BedrockStuff.MODID);

    public static final RegistryObject<Item> bedrockScrap = ITEMS.register("bedrock_scrap", () -> new Item(new Item.Properties().stacksTo(64).tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> bedrockIngot = ITEMS.register("bedrock_ingot", () -> new Item(new Item.Properties().stacksTo(64).tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> bedrockOre = ITEMS.register("bedrock_ore", () -> new BlockItem(RegisterBlocks.bedrockOre.get(), new Item.Properties().stacksTo(64).tab(CreativeModeTab.TAB_MATERIALS)));
}
