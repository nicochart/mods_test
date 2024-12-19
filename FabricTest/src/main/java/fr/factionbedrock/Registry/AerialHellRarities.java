package fr.factionbedrock.Registry;

import fr.factionbedrock.Mixin.RarityMixin;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;

public class AerialHellRarities
{
    public static Rarity LEGENDARY = RarityMixin.invokeConstructor("legendary", 4, 4, "aerial_hell_legendary", Formatting.GOLD);

    public static void load() {}
}
