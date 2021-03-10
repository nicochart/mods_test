package fr.factionbedrock.newdim.World;

public class NewDimSeed
{
	private static long seed = 0;

    public static void putInSeed(long seedInput)
    {
        seed = seedInput;
    }

    public static long getSeed()
    {
        return seed;
    }
}
