package fr.factionbedrock.newdim.Setup;

import fr.factionbedrock.newdim.NewDimension;
import fr.factionbedrock.newdim.Client.NewDimRendering;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = NewDimension.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event)
    {
    	NewDimRendering.registerBlockRenderLayers();
    }
}