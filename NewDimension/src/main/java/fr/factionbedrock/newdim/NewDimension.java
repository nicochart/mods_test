package fr.factionbedrock.newdim;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.factionbedrock.newdim.Register.RegisterBiomes;
import fr.factionbedrock.newdim.Register.RegisterBlocks;
import fr.factionbedrock.newdim.Register.RegisterItems;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

@Mod(NewDimension.MODID) 

public class NewDimension
{
	public static final String MODID = "newdim";
	public static final String NAME = "New Dimension";
	public static final String VERSION = "1.0";
	
	private static Logger LOGGER = LogManager.getLogger();
	
	/*Code du ExampleMod*/
	
    public NewDimension()
    {
    	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        
    	bus.addListener(this::setup);
		bus.addListener(this::clientSetup);
		
		IEventBus forgeBus = MinecraftForge.EVENT_BUS;
		//forgeBus.addListener(EventPriority.NORMAL, NewDimStructures::addDimensionalSpacing);
		
		DeferredRegister<?>[] registers =
		{
				RegisterBiomes.BIOMES,
				RegisterBlocks.BLOCKS,
				RegisterItems.ITEMS,
		};
		for (DeferredRegister<?> register : registers) {register.register(bus);}
    }

    private void setup(FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event)
    {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
	
}
