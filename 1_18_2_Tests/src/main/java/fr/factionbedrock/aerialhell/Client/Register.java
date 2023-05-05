package fr.factionbedrock.aerialhell.Client;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AerialHell.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Register
{
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(AerialHellEntities.MUD_GOLEM.get(), MudGolemRender::new);
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(MudGolemModel.MUD_GOLEM, MudGolemModel::createBodyLayer);
    }

    public static void registerBlockRenderLayers()
    {
        RenderType translucent = RenderType.translucent();
        RenderType cutout = RenderType.cutout();
        RenderType cutout_mipped = RenderType.cutoutMipped();

        ItemBlockRenderTypes.setRenderLayer(AerialHellBlocks.STELLAR_GRASS_BLOCK.get(), cutout_mipped);
        ItemBlockRenderTypes.setRenderLayer(AerialHellBlocks.SHADOW_GRASS_BLOCK.get(), cutout_mipped);
        ItemBlockRenderTypes.setRenderLayer(AerialHellBlocks.STELLAR_GRASS.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(AerialHellBlocks.SHADOW_GRASS.get(), cutout);
    }
}