package fr.factionbedrock.bedrockstuff.Event;

import fr.factionbedrock.bedrockstuff.WorldGen.GenOverworldOres;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
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
		 if(event.getCategory() != Biome.Category.THEEND && event.getCategory() != Biome.Category.NETHER)
		 {
			 for (int i = 0; i < 5; i++)
			 {
                 event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GenOverworldOres.BEDROCK_ORE);
             }
		 }
	 }
}
