package fr.factionbedrock.Client.BlockBakedModels;

import fr.factionbedrock.Registry.AerialHellBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.BlockModels;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;

public class AerialHellShiftingBakedModels
{
    public static void registerShiftingBakedModels()
    {
        ModelLoadingPlugin.register(plugin -> plugin.modifyModelAfterBake().register((original, context) ->
        {
            if (validateContext(context, AerialHellBlocks.AERIAL_TREE_PLANKS))
            {
                ModelIdentifier shiftedIdentifier = BlockModels.getModelId(Blocks.GLOWSTONE.getDefaultState());
                BakedModel initialModel = original;
                BakedModel shiftedModel = context.loader().getBakedModelMap().get(shiftedIdentifier);
                //context.loader().getBakedModelMap().put(initialIdentifier, editedModel);
                if (initialModel != null && shiftedModel != null)
                {
                    BakedModel editedModel = new ShiftingBlockBakedModel(initialModel, shiftedModel, (forceShifted) -> doesCurrentPlayerHaveNightVision() || forceShifted);
                    return editedModel;
                }
                else
                {
                    //System.out.println("VALIDATED CONTEXT BUT NULL FOUND : initialModel = "+initialModel+", shiftedModel = "+shiftedModel);
                }
            }
            return original;
        }));
    }

    private static boolean validateContext(ModelModifier.AfterBake.Context context, Block block)
    {
        if (context.topLevelId() == null) {return false;}
        return context.topLevelId().id().equals(Registries.BLOCK.getId(block));
    }

    public static boolean doesCurrentPlayerHaveNightVision() {return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT && MinecraftClient.getInstance().player != null && MinecraftClient.getInstance().player.hasStatusEffect(StatusEffects.NIGHT_VISION);}
}
