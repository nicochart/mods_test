package fr.factionbedrock.newdim.Client;

import com.google.common.base.Supplier;

import fr.factionbedrock.newdim.NewDimension;
import fr.factionbedrock.newdim.Setup.Registration;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NewDimension.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
@OnlyIn(Dist.CLIENT)
public class NewDimRendering
{
	public static void registerBlockRenderLayers()
	{
		RenderType translucent = RenderType.getTranslucent();
		render(() -> Registration.NEWDIM_WHITE_AERCLOUD.get(), translucent);
		render(() -> Registration.NEWDIM_QUICKSOIL_AERCLOUD.get(), translucent);
		render(() -> Registration.NEWDIM_BLUE_AERCLOUD.get(), translucent);
		render(() -> Registration.NEWDIM_GOLDEN_AERCLOUD.get(), translucent);
	}
	
	private static void render(Supplier<? extends Block> block, RenderType render)
	{
        RenderTypeLookup.setRenderLayer(block.get(), render);
    }
}
