package fr.factionbedrock.newdim.Client.EntityRender;

import com.mojang.blaze3d.matrix.MatrixStack;

import fr.factionbedrock.newdim.NewDimension;
import fr.factionbedrock.newdim.Client.EntityModels.NewDimGolemModel;
import fr.factionbedrock.newdim.Entity.NewDimGolem;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NewDimGolemRender extends MobRenderer<NewDimGolem, NewDimGolemModel<NewDimGolem>>
{

    private static final ResourceLocation texture = new ResourceLocation(NewDimension.MODID, "textures/entity/newdim_golem.png");

    public NewDimGolemRender(EntityRendererManager manager)
    {
        super(manager, new NewDimGolemModel(), 0.6f);
    }

    @Override
    public ResourceLocation getEntityTexture(NewDimGolem entity)
    {return texture;}

    @Override
    public void applyRotations(NewDimGolem entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks)
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