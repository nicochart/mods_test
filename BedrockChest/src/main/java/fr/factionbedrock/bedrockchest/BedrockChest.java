package fr.factionbedrock.bedrockchest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.factionbedrock.bedrockchest.Registry.RegisterBlocks;
import fr.factionbedrock.bedrockchest.Registry.RegisterItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;





@Mod(BedrockChest.MODID) 

public class BedrockChest
{
	public static final String MODID = "bedrockchest";
	public static final String NAME = "Bedrock Chest";
	public static final String VERSION = "1.0";
	
	private static Logger LOGGER = LogManager.getLogger();
	
	
	/*Code du ExampleMod*/
	
    public BedrockChest()
    {
    	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        
    	bus.addListener(this::setup);
		bus.addListener(this::clientSetup);
		
		IEventBus forgeBus = MinecraftForge.EVENT_BUS;
		//forgeBus.addListener(EventPriority.NORMAL, NewDimStructures::addDimensionalSpacing);
		
		DeferredRegister<?>[] registers =
		{
				RegisterBlocks.BLOCKS,
				RegisterItems.ITEMS
		};
		for (DeferredRegister<?> register : registers) {register.register(bus);}
    }

    
    private void setup(FMLCommonSetupEvent event)
    {
        // some preinit code
    	
    }

    @OnlyIn(Dist.CLIENT)
    private void clientSetup(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
    	
    	//Chests render
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event)
    {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
	
}
