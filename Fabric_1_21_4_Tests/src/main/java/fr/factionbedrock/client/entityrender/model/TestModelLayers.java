package fr.factionbedrock.client.entityrender.model;

import fr.factionbedrock.FabricTest;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class TestModelLayers
{
    public static final EntityModelLayer CUBE = createEntityModelLayer("cube");

    private static EntityModelLayer createEntityModelLayer(String id) {return createEntityModelLayer(id, "main");}

    private static EntityModelLayer createEntityModelLayer(String id, String layer)
    {
        return new EntityModelLayer(FabricTest.id(id), layer);
    }
}
