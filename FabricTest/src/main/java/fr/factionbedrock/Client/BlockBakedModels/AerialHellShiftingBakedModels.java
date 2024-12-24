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
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class AerialHellShiftingBakedModels
{
    public static void registerShiftingBakedModels()
    {
        ModelLoadingPlugin.register(plugin -> plugin.modifyModelAfterBake().register((initialModel, context) ->
        {
            if (validateContext(context, AerialHellBlocks.AERIAL_TREE_PLANKS))
            {
                ModelIdentifier shiftedIdentifier = BlockModels.getModelId(Blocks.GLOWSTONE.getDefaultState());
                BakedModel shiftedModel = context.loader().getBakedModelMap().get(shiftedIdentifier);
                if (initialModel != null && shiftedModel != null)
                {
                    return new ShiftingBlockBakedModel(initialModel, shiftedModel, (forceShifted) -> doesCurrentPlayerHaveNightVision() || forceShifted);
                }
            }
            else if (validateContext(context, Blocks.CHERRY_STAIRS))
            {
                System.out.println("variant = "+context.topLevelId().getVariant());
                Identifier shiftedId = Registries.BLOCK.getId(Blocks.BLACKSTONE_STAIRS);
                ModelIdentifier shiftedIdentifier = new ModelIdentifier(shiftedId, context.topLevelId().getVariant());

                BakedModel shiftedModel = context.loader().getBakedModelMap().get(shiftedIdentifier); //sometimes null : modelBake is not finished, the BakedModelMap is not complete.
                if (initialModel != null && shiftedModel != null)
                {
                    return new ShiftingBlockBakedModel(initialModel, shiftedModel, (forceShifted) -> doesCurrentPlayerHaveNightVision() || forceShifted);
                }
                else {System.out.println("NULL FOUND : "+(initialModel == null ? "initialModel " : "")+(shiftedModel == null ? "shiftedModel " : "")+", shiftedIdentifier = "+shiftedIdentifier+", shiftedId = "+shiftedId);}
            }
            return initialModel;
        }));
    }

    private static boolean validateContext(ModelModifier.AfterBake.Context context, Block block)
    {
        if (context.topLevelId() == null) {return false;}
        return context.topLevelId().id().equals(Registries.BLOCK.getId(block));
    }

    public static boolean doesCurrentPlayerHaveNightVision() {return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT && MinecraftClient.getInstance().player != null && MinecraftClient.getInstance().player.hasStatusEffect(StatusEffects.NIGHT_VISION);}
}
