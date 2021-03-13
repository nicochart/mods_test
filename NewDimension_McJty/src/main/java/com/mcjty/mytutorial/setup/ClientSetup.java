package com.mcjty.mytutorial.setup;

import com.mcjty.mytutorial.MyTutorial;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = MyTutorial.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup
{
	public static void init(final FMLCommonSetupEvent event)
	{}
	
}
