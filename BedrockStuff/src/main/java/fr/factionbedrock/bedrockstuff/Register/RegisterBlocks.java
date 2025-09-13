package fr.factionbedrock.bedrockstuff.Register;

import fr.factionbedrock.bedrockstuff.BedrockStuff;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RegisterBlocks
{
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BedrockStuff.MODID);

	public static final DeferredBlock<Block> BEDROCK_ORE = BLOCKS.register(Keys.BEDROCK_ORE.location().getPath(), () -> new DropExperienceBlock
	(
			UniformInt.of(4, 8),
			BlockBehaviour.Properties.of()
					.setId(Keys.BEDROCK_ORE)
					.mapColor(MapColor.STONE)
					.strength(90f, 100f)
					.sound(SoundType.STONE)
					.requiresCorrectToolForDrops()
	));

	public static final DeferredBlock<Block> DEEPSLATE_BEDROCK_ORE = BLOCKS.register(Keys.DEEPSLATE_BEDROCK_ORE.location().getPath(), () -> new DropExperienceBlock
	(
			UniformInt.of(4, 8),
			BlockBehaviour.Properties.of()
					.setId(Keys.DEEPSLATE_BEDROCK_ORE)
					.mapColor(MapColor.STONE)
					.strength(90f, 100f)
					.sound(SoundType.STONE)
					.requiresCorrectToolForDrops()
	));

	public static final DeferredBlock<Block> RAW_BEDROCK_BLOCK = BLOCKS.register(Keys.RAW_BEDROCK_BLOCK.location().getPath(), () -> new Block
	(
			BlockBehaviour.Properties.of()
					.setId(Keys.RAW_BEDROCK_BLOCK)
					.mapColor(MapColor.STONE)
					.strength(90f, 100f)
					.sound(SoundType.STONE)
					.requiresCorrectToolForDrops()
	));

	public static final DeferredBlock<Block> BEDROCK_BLOCK = BLOCKS.register(Keys.BEDROCK_BLOCK.location().getPath(), () -> new Block
	(
			BlockBehaviour.Properties.of()
					.setId(Keys.BEDROCK_BLOCK)
					.mapColor(MapColor.STONE)
					.strength(120f, 100f)
					.sound(SoundType.STONE)
					.requiresCorrectToolForDrops()
	));

	public static class Keys
	{
		public static final ResourceKey<Block> BEDROCK_ORE = createKey("bedrock_ore");
		public static final ResourceKey<Block> DEEPSLATE_BEDROCK_ORE = createKey("deepslate_bedrock_ore");
		public static final ResourceKey<Block> RAW_BEDROCK_BLOCK = createKey("raw_bedrock_block");
		public static final ResourceKey<Block> BEDROCK_BLOCK = createKey("bedrock_block");

		private static ResourceKey<Block> createKey(String name)
		{
			return ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(BedrockStuff.MODID, name));
		}
	}
}
