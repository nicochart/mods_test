package fr.factionbedrock.bedrockstuff.Register;

import fr.factionbedrock.bedrockstuff.BedrockStuff;
import fr.factionbedrock.bedrockstuff.Basis.BasisToolMaterial;
import fr.factionbedrock.bedrockstuff.Tools.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = BedrockStuff.MODID, bus = Bus.MOD)
public class RegisterTools
{
	public static final DeferredRegister<Item> TOOLS = DeferredRegister.create(ForgeRegistries.ITEMS, BedrockStuff.MODID);

	public static final RegistryObject<Item> bedrockSword = TOOLS.register("bedrock_sword", () -> new Sword(BasisToolMaterial.bedrock, new Item.Properties()));
	public static final RegistryObject<Item> bedrockHoe = TOOLS.register("bedrock_hoe", () -> new Hoe(BasisToolMaterial.bedrock,-3, 0.0F, new Item.Properties()));
	public static final RegistryObject<Item> bedrockAxe = TOOLS.register("bedrock_axe", () -> new Axe(BasisToolMaterial.bedrock, new Item.Properties()));
	public static final RegistryObject<Item> bedrockPickaxe = TOOLS.register("bedrock_pickaxe", () -> new Pickaxe(BasisToolMaterial.bedrock, new Item.Properties()));
	public static final RegistryObject<Item> bedrockShovel = TOOLS.register("bedrock_shovel", () -> new Shovel(BasisToolMaterial.bedrock, new Item.Properties()));
}
