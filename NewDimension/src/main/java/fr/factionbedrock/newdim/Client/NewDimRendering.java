package fr.factionbedrock.newdim.Client;

import com.google.common.base.Supplier;

import fr.factionbedrock.newdim.NewDimension;
import fr.factionbedrock.newdim.Client.EntityRender.NewDimGolemRender;
import fr.factionbedrock.newdim.Setup.Registration;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NewDimension.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
@OnlyIn(Dist.CLIENT)
public class NewDimRendering
{
	public static void registerBlockRenderLayers()
	{
		RenderType translucent = RenderType.getTranslucent();
		RenderType cutout = RenderType.getCutout();
		
		render(() -> Registration.NEWDIM_WHITE_AERCLOUD.get(), translucent);
		render(() -> Registration.NEWDIM_QUICKSOIL_AERCLOUD.get(), translucent);
		render(() -> Registration.NEWDIM_BLUE_AERCLOUD.get(), translucent);
		render(() -> Registration.NEWDIM_GOLDEN_AERCLOUD.get(), translucent);
		render(() -> Registration.NEWDIM_GREEN_AERCLOUD.get(), translucent);
        render(() -> Registration.NEWDIM_BERRY_BUSH.get(), cutout);
        render(() -> Registration.NEWDIM_BERRY_BUSH_STEM.get(), cutout);
        render(() -> Registration.PURPLE_FLOWER.get(), cutout);
        render(() -> Registration.WHITE_FLOWER.get(), cutout);
        render(() -> Registration.NEWDIM_PORTAL.get(), translucent);
	}
	
	private static void render(Supplier<? extends Block> block, RenderType render)
	{
        RenderTypeLookup.setRenderLayer(block.get(), render);
    }
	
	public static void registerEntityRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(Registration.NEWDIM_GOLEM.get(), NewDimGolemRender::new);
	}
}
