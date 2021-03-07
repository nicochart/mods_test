package fr.factionbedrock.bedrockchest.Registry;

import fr.factionbedrock.bedrockchest.BedrockChest;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = BedrockChest.MODID, bus = Bus.MOD)
public class RegisterItems
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BedrockChest.MODID);
}