package fr.factionbedrock.newdim.Register;

import fr.factionbedrock.newdim.NewDimension;
import fr.factionbedrock.newdim.Item.NewDimItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = NewDimension.MODID, bus = Bus.MOD)
public class RegisterItems
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NewDimension.MODID);

    public static final Rarity FORGOTTEN = Rarity.create("forgotten", TextFormatting.GREEN);
    
    public static final RegistryObject<Item> BEDROCK_INGOT = ITEMS.register("bedrock_ingot", NewDimItem::new);
}
