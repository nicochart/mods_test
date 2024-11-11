package fr.factionbedrock.Setup;

import fr.factionbedrock.Registry.AerialHellBlocks;
import fr.factionbedrock.Registry.AerialHellItems;
import fr.factionbedrock.Registry.AerialHellItemGroups;

public class AerialHellSetup
{
    public static void registration()
    {
        AerialHellBlocks.load();
        AerialHellItems.load();
        AerialHellItemGroups.load();
        AerialHellItemGroups.addItemsToVanillaGroups();
    }
}
