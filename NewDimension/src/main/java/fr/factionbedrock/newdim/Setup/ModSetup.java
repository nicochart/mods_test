package fr.factionbedrock.newdim.Setup;

import fr.factionbedrock.newdim.NewDimension;
import fr.factionbedrock.newdim.World.NewDimBiomeProvider;
import fr.factionbedrock.newdim.World.NewDimChunkGenerator;
import fr.factionbedrock.newdim.Commands.ModCommands;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = NewDimension.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {

	public static final ItemGroup ITEM_GROUP = new ItemGroup("newdim_group")
	{
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(Registration.BEDROCK_INGOT.get()); //icône du groupe d'items (Lingot de Bedrock temporairement)
        }
    };

    public static void init(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
        	Registration.registerConfiguredFeatures();
        	
        	Registry.register(Registry.CHUNK_GENERATOR_CODEC, new ResourceLocation(NewDimension.MODID, "newdimchunkgen"),
                    NewDimChunkGenerator.CODEC);
            Registry.register(Registry.BIOME_PROVIDER_CODEC, new ResourceLocation(NewDimension.MODID, "newdimbiomeprovider"),
                    NewDimBiomeProvider.CODEC);
        });
    }


    @SubscribeEvent
    public static void serverLoad(RegisterCommandsEvent event) {
        ModCommands.register(event.getDispatcher());
    }
}
