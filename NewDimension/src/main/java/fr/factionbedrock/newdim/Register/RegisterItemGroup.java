package fr.factionbedrock.newdim.Register;

import fr.factionbedrock.newdim.NewDimension;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = NewDimension.MODID, bus = Bus.MOD)
public class RegisterItemGroup
{
	public static final ItemGroup GROUP = new ItemGroup("newdim_group")
	{
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(RegisterItems.BEDROCK_INGOT.get()); //icône du groupe d'items (Lingot de Bedrock temporairement)
        }
    };
}
