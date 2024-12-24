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
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AerialHellShiftingBakedModels
{
    private static final List<Block> toBakeList = new ArrayList<>(Arrays.asList(
            AerialHellBlocks.AERIAL_TREE_PLANKS
    ));

    public static void registerShiftingBakedModels()
    {
        ModelLoadingPlugin.register(plugin -> plugin.modifyModelAfterBake().register((original, context) ->
        {
            if (toBakeList.isEmpty()) {return original;}
            List<Block> bakedList = new ArrayList<>();
            for (Block block : toBakeList)
            {
                ModelIdentifier initialIdentifier = BlockModels.getModelId(block.getDefaultState());
                ModelIdentifier shiftedIdentifier = BlockModels.getModelId(Blocks.GLOWSTONE.getDefaultState());
                BakedModel initialModel = context.loader().getBakedModelMap().get(initialIdentifier);
                BakedModel shiftedModel = context.loader().getBakedModelMap().get(shiftedIdentifier);
                if (initialModel != null && shiftedModel != null)
                {
                    BakedModel editedModel = new ShiftingBlockBakedModel(initialModel, shiftedModel, (forceShifted) -> doesCurrentPlayerHaveNightVision() || forceShifted);
                    context.loader().getBakedModelMap().put(initialIdentifier, editedModel);
                    bakedList.add(block);
                }
            }
            toBakeList.removeAll(bakedList);
            return original;
        }));
    }

    private static ModelIdentifier getModelIdentifierFromContextVariant(ModelModifier.AfterBake.Context context, Block block) //unused
    {
        Identifier identifier = Registries.BLOCK.getId(block);
        return new ModelIdentifier(identifier, context.topLevelId().getVariant());
    }

    public static boolean doesCurrentPlayerHaveNightVision() {return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT && MinecraftClient.getInstance().player != null && MinecraftClient.getInstance().player.hasStatusEffect(StatusEffects.NIGHT_VISION);}
}
