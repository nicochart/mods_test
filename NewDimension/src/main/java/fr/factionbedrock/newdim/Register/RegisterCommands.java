package fr.factionbedrock.newdim.Register;

import fr.factionbedrock.newdim.NewDimension;
import fr.factionbedrock.newdim.Commands.ModCommands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = NewDimension.MODID, bus = Bus.FORGE)
public class RegisterCommands
{
	@SubscribeEvent
    public static void serverLoad(RegisterCommandsEvent event)
	{
        ModCommands.register(event.getDispatcher());
    }
}
