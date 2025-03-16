package fr.factionbedrock.client;

import fr.factionbedrock.client.bakedmodels.ShiftingBlockBakedModel;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.model.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;

public class RenderRegistration
{
    public static void registerShiftingBakedModels()
    {
        ModelLoadingPlugin.register(plugin -> plugin.modifyModelAfterBake().register((original, context) ->
        {
            Baker baker = context.baker();
            if (context.id().toString().equals("test_mod:block/example_block"))
            {
                BakedModel glowstoneBakedModel = baker.bake(Identifier.ofVanilla("block/glowstone"), ModelRotation.X0_Y0);
                BakedModel editedModel = new ShiftingBlockBakedModel(original, glowstoneBakedModel, (forceShifted) -> MinecraftClient.getInstance().player != null && MinecraftClient.getInstance().player.hasStatusEffect(StatusEffects.NIGHT_VISION) || forceShifted);
                return editedModel;
            }
            else {return original;}
        }));
    }

    public static void makePressedCeilingStoneButtonRenderLikeGlowstone()
    {
        ModelLoadingPlugin.register(plugin -> plugin.modifyModelAfterBake().register((original, context) ->
        {
            Baker baker = context.baker();
            if (context.id().toString().equals("minecraft:block/stone_button_pressed") && context.baker().getModelNameSupplier().get().contains("face=ceiling"))
            {
                BakedModel glowstoneBakedModel = baker.bake(Identifier.ofVanilla("block/glowstone"), ModelRotation.X0_Y0);
                return glowstoneBakedModel;
            }
            else {return original;}
        }));
    }

    public static void makeEveryBlockRenderLikeStone()
    {
        ModelLoadingPlugin.register(plugin -> plugin.modifyModelAfterBake().register((original, context) ->
        {
            Baker baker = context.baker();
            if (!context.id().toString().equals("minecraft:block/stone"))
            {
                BakedModel newModel = baker.bake(Identifier.ofVanilla("block/stone"), ModelRotation.X0_Y0);
                return newModel;
            }
            else {return original;}
        }));
    }
}
