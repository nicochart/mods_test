package fr.factionbedrock.Setup;

import fr.factionbedrock.Registry.AerialHellBlocks;
import fr.factionbedrock.Registry.AerialHellEntityTypes;
import fr.factionbedrock.Registry.AerialHellItems;
import fr.factionbedrock.Registry.AerialHellItemGroups;
import fr.factionbedrock.Registry.Worldgen.AerialHellDimensions;

public class AerialHellSetup
{
    public static void init()
    {
        AerialHellBlocks.load();
        AerialHellItems.load();
        AerialHellItemGroups.load();
        AerialHellItemGroups.addItemsToVanillaGroups();
        AerialHellEntityTypes.load();
        AerialHellDimensions.makePortal();
    }
}
