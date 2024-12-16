package fr.factionbedrock.Client.BlockBakedModels;

import fr.factionbedrock.Registry.AerialHellBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.BlockModels;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.entity.effect.StatusEffects;

public class AerialHellShiftingBakedModels
{
    public static void registerShiftingBakedModels()
    {
        ModelLoadingPlugin.register(plugin -> plugin.modifyModelAfterBake().register((original, context) ->
        {
            ModelIdentifier initialIdentifier = BlockModels.getModelId(AerialHellBlocks.AERIAL_TREE_PLANKS.getDefaultState());
            ModelIdentifier shiftedIdentifier = BlockModels.getModelId(Blocks.GLOWSTONE.getDefaultState());
            BakedModel initialModel = context.loader().getBakedModelMap().get(initialIdentifier);
            BakedModel shiftedModel = context.loader().getBakedModelMap().get(shiftedIdentifier);
            BakedModel editedModel = new ShiftingBlockBakedModel(initialModel, shiftedModel, (forceShifted) -> doesCurrentPlayerHaveNightVision() || forceShifted);
            context.loader().getBakedModelMap().put(initialIdentifier, editedModel);
            return original;
        }));
    }

    public static boolean doesCurrentPlayerHaveNightVision() {return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT && MinecraftClient.getInstance().player != null && MinecraftClient.getInstance().player.hasStatusEffect(StatusEffects.NIGHT_VISION);}
}
