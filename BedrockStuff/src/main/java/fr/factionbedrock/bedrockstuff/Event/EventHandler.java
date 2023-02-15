package fr.factionbedrock.bedrockstuff.Event;

import fr.factionbedrock.bedrockstuff.Register.RegisterConfiguredFeatures;
import fr.factionbedrock.bedrockstuff.Register.RegisterPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
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
			 for (int i = 0; i < 5; i++)
			 {
				 //does not work
				 //event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, RegisterPlacedFeatures.bedrockOre.getHolder().get());
				 //event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(RegisterPlacedFeatures.bedrockOre.getHolder().get());
             }
		 }
	 }
}
