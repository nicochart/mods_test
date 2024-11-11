package fr.factionbedrock.Setup;

import fr.factionbedrock.Registry.AerialHellBlocks;
import fr.factionbedrock.Registry.AerialHellItems;
import fr.factionbedrock.Registry.ItemGroups;

public class AerialHellSetup
{
    public static void registration()
    {
        AerialHellBlocks.load();
        AerialHellItems.load();
        ItemGroups.load();
    }
}
