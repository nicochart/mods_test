package fr.factionbedrock.client;

import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.render.model.*;
import net.minecraft.util.Identifier;

public class RenderRegistration
{
    public static void registerShiftingBakedModels()
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
