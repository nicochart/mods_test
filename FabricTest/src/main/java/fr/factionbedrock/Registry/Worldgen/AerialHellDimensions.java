package fr.factionbedrock.Registry.Worldgen;

import fr.factionbedrock.AerialHell;
import fr.factionbedrock.Registry.AerialHellItems;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class AerialHellDimensions
{
    public static final RegistryKey<DimensionType> AERIAL_HELL_DIMENSION_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE, name("aerial_hell"));
    public static final RegistryKey<World> AERIAL_HELL_DIMENSION = RegistryKey.of(RegistryKeys.WORLD, name("aerial_hell"));

    private static Identifier name(String name) {return Identifier.of(AerialHell.MOD_ID, name);}

    public static void makePortal()
    {
        CustomPortalBuilder.beginPortal()
                .frameBlock(Blocks.STONE_BRICKS)
                .lightWithItem(AerialHellItems.SKY_STICK)
                .destDimID(Identifier.of(AerialHell.MOD_ID, "aerial_hell"))
                .tintColor(15, 110, 0)
                .registerPortal();
    }
}
