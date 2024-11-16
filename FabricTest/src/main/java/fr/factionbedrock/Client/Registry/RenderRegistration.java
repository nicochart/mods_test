package fr.factionbedrock.Client.Registry;

import fr.factionbedrock.Client.BlockEntityRenderer.AerialHellChestBlockEntityRenderer;
import fr.factionbedrock.Client.EntityRenderers.EvilCowRender;
import fr.factionbedrock.Registry.AerialHellBlockEntities;
import fr.factionbedrock.Registry.AerialHellEntityTypes;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

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

    public static void registerBlockEntityRenderers()
    {
        BlockEntityRendererFactories.register(AerialHellBlockEntities.CHEST, AerialHellChestBlockEntityRenderer::new);
    }
}
