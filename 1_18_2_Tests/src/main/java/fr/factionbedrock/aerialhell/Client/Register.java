package fr.factionbedrock.aerialhell.Client;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
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
}
