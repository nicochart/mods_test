package fr.factionbedrock.aerialhell.Client;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;

@Mod.EventBusSubscriber(modid = AerialHell.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HandleColors
{
    @SubscribeEvent
    public static void handleBlockColors(ColorHandlerEvent.Block event)
    {
        event.getBlockColors().register((state, world, pos, tint) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : new Color(12, 35, 26).getRGB(),
                AerialHellBlocks.STELLAR_GRASS_BLOCK.get(),
                AerialHellBlocks.STELLAR_GRASS.get()
        );
    }

    @SubscribeEvent
    public static void handleItemColors(ColorHandlerEvent.Item event)
    {
        event.getItemColors().register((stack, color) -> new Color(50, 140, 102).getRGB(),
                AerialHellItems.STELLAR_GRASS_BLOCK.get(),
                AerialHellItems.STELLAR_GRASS.get()
        );
    }
}
