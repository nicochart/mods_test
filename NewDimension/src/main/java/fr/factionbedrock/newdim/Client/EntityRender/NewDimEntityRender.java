package fr.factionbedrock.newdim.Client.EntityRender;

import com.mojang.blaze3d.matrix.MatrixStack;

import fr.factionbedrock.newdim.NewDimension;
import fr.factionbedrock.newdim.Client.EntityModels.NewDimEntityModel;
import fr.factionbedrock.newdim.Entity.NewDimEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NewDimEntityRender extends MobRenderer<NewDimEntity, NewDimEntityModel<NewDimEntity>>
{

    private static final ResourceLocation texture = new ResourceLocation(NewDimension.MODID, "textures/entity/newdim_entity.png");

    public NewDimEntityRender(EntityRendererManager manager)
    {
        super(manager, new NewDimEntityModel(), 0.6f);
        this.addLayer(new NewDimEntityEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(NewDimEntity entity)
    {return texture;}

    @Override
    public void applyRotations(NewDimEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (!((double)entityLiving.limbSwingAmount < 0.01D))
        {
            float f1 = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(6.5F * f2));
        }
    }
}