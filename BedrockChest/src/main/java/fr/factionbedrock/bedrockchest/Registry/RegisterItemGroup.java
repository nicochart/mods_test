package fr.factionbedrock.bedrockchest.Registry;

import fr.factionbedrock.bedrockchest.BedrockChest;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = BedrockChest.MODID, bus = Bus.MOD)
public class RegisterItemGroup
{
	public static final ItemGroup GROUP = new ItemGroup("bedrockchest_group")
	{
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(Blocks.BEDROCK); //icône du groupe d'items (Bedrock temporairement)
        }
    };
}
