package fr.factionbedrock.Client.BlockBakedModels;

import fr.factionbedrock.Registry.AerialHellBlocks;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.block.BlockModels;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.ModelIdentifier;

public class AerialHellShiftingBakedModels
{
    public static void registerShiftingBakedModels()
    {
        ModelLoadingPlugin.register(plugin -> plugin.modifyModelAfterBake().register((original, context) ->
        {
            ModelIdentifier initialIdentifier = BlockModels.getModelId(AerialHellBlocks.AERIAL_TREE_PLANKS.getDefaultState());
            BakedModel editedModel = context.loader().getBakedModelMap().get(BlockModels.getModelId(Blocks.GLOWSTONE.getDefaultState()));
            context.loader().getBakedModelMap().put(initialIdentifier, editedModel);
            return original;
        }));
    }
}
