package fr.factionbedrock.newdim.Client;

import fr.factionbedrock.newdim.NewDimension;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber(modid = NewDimension.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NewChestModel
{
	public static final ResourceLocation NEWCHEST_LOCATION = new ResourceLocation(NewDimension.MODID, "entity/newchest/newchest");
	
	@SubscribeEvent
	public static void onStitch(TextureStitchEvent.Pre event)
	{
	    if (!event.getMap().getTextureLocation().equals(Atlases.CHEST_ATLAS))
	    {
	      return;
	    }
	    event.addSprite(NEWCHEST_LOCATION);
	}
}
