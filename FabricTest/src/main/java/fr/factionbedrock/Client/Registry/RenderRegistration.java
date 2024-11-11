package fr.factionbedrock.Client.Registry;

import fr.factionbedrock.Client.EntityRenderers.EvilCowRender;
import fr.factionbedrock.Registry.AerialHellEntityTypes;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class RenderRegistration
{
    public static void registerEntityModelLayers()
    {
        //EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.EVIL_COW, EvilCowModel::createBodyLayer);
    }

    public static void registerEntityRenderers()
    {
        EntityRendererRegistry.register(AerialHellEntityTypes.EVIL_COW, EvilCowRender::new);
    }
}
