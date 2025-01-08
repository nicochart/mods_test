package fr.factionbedrock.Setup;

import fr.factionbedrock.Client.BlockBakedModels.AerialHellShiftingBakedModels;
import fr.factionbedrock.Client.Registry.RenderRegistration;
import fr.factionbedrock.Client.World.AerialHellDimensionSpecialEffects;
import fr.factionbedrock.Registry.Worldgen.AerialHellDimensions;
import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.minecraft.client.render.DimensionEffects;

public class AerialHellClientSetup
{
    public static void init()
    {
        RenderRegistration.registerEntityModelLayers();
        RenderRegistration.registerEntityRenderers();
        RenderRegistration.registerBlockEntityRenderers();
        AerialHellClientSetup.registerDimensionRenderInfo();
        AerialHellShiftingBakedModels.registerShiftingBakedModels();
    }

    public static void registerDimensionRenderInfo()
    {
        AerialHellDimensionSpecialEffects renderInfo = new AerialHellDimensionSpecialEffects(Float.NaN, false, DimensionEffects.SkyType.NONE, false, false);
        DimensionRenderingRegistry.registerSkyRenderer(AerialHellDimensions.AERIAL_HELL_DIMENSION, renderInfo);
        DimensionRenderingRegistry.registerCloudRenderer(AerialHellDimensions.AERIAL_HELL_DIMENSION, new AerialHellDimensionSpecialEffects.AerialHellCloudRenderer());
    }
}
