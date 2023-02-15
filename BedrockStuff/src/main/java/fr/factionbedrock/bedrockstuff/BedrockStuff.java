package fr.factionbedrock.bedrockstuff;

import java.util.stream.Collectors;

import fr.factionbedrock.bedrockstuff.Register.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BedrockStuff.MODID) 

public class BedrockStuff
{
	public static final String MODID = "bedrockstuff";
	public static final String NAME = "Bedrock Sword";
	public static final String VERSION = "1.0";
	
	private static Logger LOGGER = LogManager.getLogger();
	
    public BedrockStuff()
    {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Registration
        RegisterBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegisterItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegisterArmor.ARMOR.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegisterTools.TOOLS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegisterConfiguredFeatures.CONFIGURED_FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegisterPlacedFeatures.PLACED_FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }
}