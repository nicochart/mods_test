package fr.factionbedrock.bedrockstuff.Event;

import fr.factionbedrock.bedrockstuff.Register.RegisterConfiguredFeatures;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EventHandler
{
	 @SubscribeEvent(priority = EventPriority.HIGH)
	 public static void BiomeLoadingEvent(BiomeLoadingEvent event)
	 {
		 if(event.getCategory() != Biome.BiomeCategory.THEEND && event.getCategory() != Biome.BiomeCategory.NETHER)
		 {
			 for (int i = 0; i < 5; i++)
			 {
				 //problem here : how to add a feature ?
                 //event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, RegisterConfiguredFeatures.bedrockOre);
             }
		 }
	 }
}
