package fr.factionbedrock.newdim;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.factionbedrock.newdim.Setup.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(NewDimension.MODID) 
public class NewDimension
{
	public static final String MODID = "newdim";
	public static final String NAME = "New Dimension";
	public static final String VERSION = "1.0";
	
	private static Logger LOGGER = LogManager.getLogger();

    public NewDimension()
    {
    	Registration.init();

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
    }
}
