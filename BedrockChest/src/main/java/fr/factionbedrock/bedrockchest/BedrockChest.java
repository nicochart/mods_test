package fr.factionbedrock.bedrockchest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.factionbedrock.bedrockchest.Client.PhysicalClientSide;
import fr.factionbedrock.bedrockchest.Registry.RegisterBlocks;
import fr.factionbedrock.bedrockchest.Registry.RegisterItems;
import fr.factionbedrock.bedrockchest.Registry.RegisterTileEntityTypes;
import fr.factionbedrock.bedrockchest.Server.PhysicalServerSide;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
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
	
	public static final IPhysicalSide SIDE = 
			DistExecutor.safeRunForDist(() -> PhysicalClientSide::new, () -> PhysicalServerSide::new);
	
	/*Code du ExampleMod*/
	
    public BedrockChest()
    {
    	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
    	
    	modEventBus.addListener(this::setup);
    	modEventBus.addListener(this::clientSetup);
		
		SIDE.setup(modEventBus, forgeEventBus);
		
		DeferredRegister<?>[] registers =
		{
				RegisterBlocks.BLOCKS,
				RegisterItems.ITEMS,
				RegisterTileEntityTypes.TILE_ENTITY_TYPES
		};
		for (DeferredRegister<?> register : registers) {register.register(modEventBus);}
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
