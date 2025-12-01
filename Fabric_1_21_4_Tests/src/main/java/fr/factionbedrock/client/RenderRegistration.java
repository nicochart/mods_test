package fr.factionbedrock.client;

import fr.factionbedrock.FabricTest;
import fr.factionbedrock.client.bakedmodels.ShiftingBlockBakedModel;
import fr.factionbedrock.client.entityrender.model.CubeModel;
import fr.factionbedrock.client.entityrender.model.TestModelLayers;
import fr.factionbedrock.client.entityrender.renderer.CubeRender;
import fr.factionbedrock.client.entityrender.renderer.EmptyRender;
import fr.factionbedrock.registry.TestEntities;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.model.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class RenderRegistration
{
    public static void registerRenderers()
    {
        EntityRendererRegistry.register(TestEntities.CUBE, EmptyRender::new);
        EntityRendererRegistry.register(TestEntities.PART, CubeRender::new);
    }

    public static void registerLayerDefinitions()
    {
        EntityModelLayerRegistry.registerModelLayer(TestModelLayers.CUBE, CubeModel::createBodyLayer);
    }

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

    public static void makeGlowstoneRenderLikePressedCeilingStoneButton()
    {
        ModelLoadingPlugin.register(plugin -> plugin.modifyModelAfterBake().register((original, context) ->
        {
            Baker baker = context.baker();
            if (context.id().toString().equals("minecraft:block/glowstone"))
            {
                BakedModel glowstoneBakedModel = baker.bake(Identifier.ofVanilla("block/stone_button_pressed"), ModelRotation.X180_Y0); //X180 = ceiling
                return glowstoneBakedModel;
            }
            else {return original;}
        }));
    }

    public static void makeGrassBlockRenderUpsideDownWithRandomRotation()
    {
        Identifier grass_block_copy_id = FabricTest.id("block/grass_block_copy");
        ModelLoadingPlugin.register(plugin -> plugin.addModels(grass_block_copy_id));

        List<ModelRotation> rotationList = new ArrayList<>();
        rotationList.add(ModelRotation.X180_Y0); rotationList.add(ModelRotation.X180_Y90); rotationList.add(ModelRotation.X180_Y180); rotationList.add(ModelRotation.X180_Y270);
        ModelLoadingPlugin.register(plugin -> plugin.modifyModelAfterBake().register((original, context) ->
        {
            Baker baker = context.baker();
            if (context.id().toString().equals("minecraft:block/grass_block"))
            {
                FabricTest.LOGGER.info(context.baker().getModelNameSupplier().get());
                boolean emptyRotationList = rotationList.isEmpty();
                ModelRotation rotation = emptyRotationList ? ModelRotation.X180_Y0 : rotationList.getFirst();
                BakedModel upsideDownGrassBakedModel = baker.bake(grass_block_copy_id, rotation); //X180 = ceiling
                if (!emptyRotationList) {rotationList.removeFirst();}
                return upsideDownGrassBakedModel;
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
