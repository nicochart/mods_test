package fr.factionbedrock.bedrockstuff.Register;

import fr.factionbedrock.bedrockstuff.BedrockStuff;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = BedrockStuff.MODID, bus = Bus.MOD)
public class RegisterBlocks
{
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BedrockStuff.MODID);

	public static final RegistryObject<Block> bedrockOre = BLOCKS.register("bedrock_ore", () -> new OreBlock
	(
			BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE)
					.strength(90f, 100f)
					.sound(SoundType.STONE)
					.requiresCorrectToolForDrops()
	));

	public static final RegistryObject<Block> deepslateBedrockOre = BLOCKS.register("deepslate_bedrock_ore", () -> new OreBlock
	(
			BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE)
					.strength(90f, 100f)
					.sound(SoundType.STONE)
					.requiresCorrectToolForDrops()
	));
}
