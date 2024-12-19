package fr.factionbedrock.Setup;

import fr.factionbedrock.Registry.*;
import fr.factionbedrock.Registry.Worldgen.AerialHellDimensions;

public class AerialHellSetup
{
    public static void init()
    {
        AerialHellRarities.load();
        AerialHellBlocks.load();
        AerialHellBlockEntities.load();
        AerialHellItems.load();
        AerialHellItemGroups.load();
        AerialHellItemGroups.addItemsToVanillaGroups();
        AerialHellEntityTypes.load();
        AerialHellDimensions.makePortal();
    }
}
