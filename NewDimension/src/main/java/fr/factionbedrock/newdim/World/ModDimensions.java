package fr.factionbedrock.newdim.World;

import fr.factionbedrock.newdim.NewDimension;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

public class ModDimensions
{
	public static final RegistryKey<DimensionType> NEW_DIMENSION_TYPE = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, name("newdimension"));
    public static final RegistryKey<World> NEW_DIMENSION = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, name("newdimension"));
    
    private static ResourceLocation name(String name)
    {
        return new ResourceLocation(NewDimension.MODID, name);
    }
}
