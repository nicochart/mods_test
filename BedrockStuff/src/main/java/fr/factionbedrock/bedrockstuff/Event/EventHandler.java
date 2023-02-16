package fr.factionbedrock.bedrockstuff.Event;

import fr.factionbedrock.bedrockstuff.Register.RegisterPlacedFeatures;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class EventHandler
{
	 @SubscribeEvent(priority = EventPriority.HIGH)
	 public static void BiomeLoadingEvent(final BiomeLoadingEvent event)
	 {
		 if (event.getCategory() != Biome.BiomeCategory.THEEND && event.getCategory() != Biome.BiomeCategory.NETHER)
		 {
			 event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(RegisterPlacedFeatures.bedrockOre.getHolder().get());
		 }
	 }
}
