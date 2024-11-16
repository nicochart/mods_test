package fr.factionbedrock.Setup;

import fr.factionbedrock.Client.Registry.RenderRegistration;

public class AerialHellClientSetup
{
    public static void init()
    {
        RenderRegistration.registerEntityModelLayers();
        RenderRegistration.registerEntityRenderers();
        RenderRegistration.registerBlockEntityRenderers();
    }
}
