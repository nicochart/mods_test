package fr.factionbedrock.Client.EntityRenderers;

import fr.factionbedrock.AerialHell;
import fr.factionbedrock.Entity.EvilCowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class EvilCowRender extends MobEntityRenderer<EvilCowEntity, CowEntityModel<EvilCowEntity>>
{
    private static final Identifier EVIL_COW_TEXTURE = Identifier.of(AerialHell.MOD_ID, "textures/entity/evil_cow/evil_cow.png");
    
    public EvilCowRender(EntityRendererFactory.Context context)
    {
        super(context, new CowEntityModel<>(context.getPart(EntityModelLayers.COW)), 0.7F);
    }

    @Override public Identifier getTexture(EvilCowEntity entity) {return EVIL_COW_TEXTURE;}
}
