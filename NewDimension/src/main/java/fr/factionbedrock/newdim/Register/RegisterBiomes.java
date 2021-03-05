package fr.factionbedrock.newdim.Register;

import fr.factionbedrock.newdim.NewDimension;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = NewDimension.MODID, bus = Bus.MOD)
public class RegisterBiomes
{
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, NewDimension.MODID);
	
	//Forêt dense de la nouvelle dimension (avec les nouveaux arbres)
	public static final RegistryKey<Biome> NEWDIM_DENSE_FOREST = register("newdim_dense_forest");
	//Plaine de la nouvelle dimension
    public static final RegistryKey<Biome> NEWDIM_PLAIN = register("newdim_plain");
    
    public static void toDictionary()
    {
        BiomeDictionary.addTypes(NEWDIM_DENSE_FOREST, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.WET, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.FOREST);
        BiomeDictionary.addTypes(NEWDIM_PLAIN, BiomeDictionary.Type.HOT, BiomeDictionary.Type.PLAINS);
    }    
	
	private static ResourceLocation name(String name)
	{
        return new ResourceLocation(NewDimension.MODID, name);
    }

    private static RegistryKey<Biome> register(String name)
    {
        BIOMES.register(name, BiomeMaker::makeVoidBiome);
        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, name(name));
    }
}
