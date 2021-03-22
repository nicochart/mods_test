package fr.factionbedrock.newdim.Client.EntityRender;

import fr.factionbedrock.newdim.NewDimension;
import fr.factionbedrock.newdim.Client.EntityModels.NewDimEntityModel;
import fr.factionbedrock.newdim.Entity.NewDimEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NewDimEntityEyesLayer<T extends NewDimEntity, M extends NewDimEntityModel<T>> extends AbstractEyesLayer<T, M>
{
    private static final RenderType TEXTURE = RenderType.getEyes(new ResourceLocation(NewDimension.MODID, "textures/entity/newdim_entity_eyes.png"));

    public NewDimEntityEyesLayer(IEntityRenderer<T, M> rendererIn)
    {
        super(rendererIn);
    }

    @Override
    public RenderType getRenderType()
    {return TEXTURE;}
}
