package fr.factionbedrock.newdim;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.serialization.Codec;

import fr.factionbedrock.newdim.Client.NewDimRendering;
import fr.factionbedrock.newdim.Register.RegisterBiomes;
import fr.factionbedrock.newdim.Register.RegisterStructureFeature;
import fr.factionbedrock.newdim.Setup.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(NewDimension.MODID) 
public class NewDimension
{
	public static final String MODID = "newdim";
	public static final String NAME = "New Dimension";
	public static final String VERSION = "1.0";
	
	public static Logger LOGGER = LogManager.getLogger();

    public NewDimension()
    {
    	Registration.init();
    	
    	
    	IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.addListener(EventPriority.NORMAL, this::addDimensionalSpacing);

        forgeBus.addListener(EventPriority.HIGH, this::biomeModification);
		
    	
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }
    public void clientSetup(FMLClientSetupEvent event)
    {
    	NewDimRendering.registerBlockRenderLayers();
    	NewDimRendering.registerEntityRenderers();
    }
    
    
    
    
    
    
    
    public void biomeModification(final BiomeLoadingEvent event)
    {	
    	/* Adding common features and structure in newdimbiome, newdim_quicksoilocean and newdim_forest */
    	if (event.getName().equals(RegisterBiomes.NEWDIMBIOME.getLocation()) || event.getName().equals(RegisterBiomes.NEWDIM_FOREST.getLocation()) || event.getName().equals(RegisterBiomes.NEWDIM_QUICKSOILOCEAN.getLocation()))
    	{
    		/* structure */
    		event.getGeneration().getStructures().add(() -> RegisterStructureFeature.CONFIGURED_SMALL_ANGELIC_TEMPLE_STRUCTURE);
    		
    		/* features */
    		event.getGeneration().getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS).add(() -> Registration.NEWDIM_QUICKSOIL_FEATURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(256).square().func_242731_b(20));
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> Registration.NEWDIM_WHITE_AERCLOUD_FEATURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(128).square().chance(5));
   		    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> Registration.NEWDIM_BLUE_AERCLOUD_FEATURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(96).square().chance(5));
   		    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> Registration.NEWDIM_GOLDEN_AERCLOUD_FEATURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(160).square().chance(5));
        }
    }    
    
    private static Method GETCODEC_METHOD;
    public void addDimensionalSpacing(final WorldEvent.Load event)
    {
        if(event.getWorld() instanceof ServerWorld){
            ServerWorld serverWorld = (ServerWorld)event.getWorld();

            try
            {
                if(GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR_CODEC.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(serverWorld.getChunkProvider().generator));
                if(cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
            }
            catch(Exception e)
            {
                NewDimension.LOGGER.error("Was unable to check if " + serverWorld.getDimensionKey().getLocation() + " is using Terraforged's ChunkGenerator.");
            }

            if(serverWorld.getChunkProvider().getChunkGenerator() instanceof FlatChunkGenerator && serverWorld.getDimensionKey().equals(World.OVERWORLD))
            {
                return;
            }

            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkProvider().generator.func_235957_b_().func_236195_a_());
            tempMap.putIfAbsent(Registration.SMALL_ANGELIC_TEMPLE_STRUCTURE.get(), DimensionStructuresSettings.field_236191_b_.get(Registration.SMALL_ANGELIC_TEMPLE_STRUCTURE.get()));
            serverWorld.getChunkProvider().generator.func_235957_b_().field_236193_d_ = tempMap;
        }
   }
}
