package fr.factionbedrock.newdim.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.newdim.Entity.NewDimEntity;
import fr.factionbedrock.newdim.Entity.NewDimGolem;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NewDimGolemModel<T extends NewDimGolem> extends EntityModel<T>
{
	/*Made with Blockbench 3.8.3
 	  Exported for Minecraft version 1.15 - 1.16*/
	
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer arm0; //leftArm
	private final ModelRenderer arm1; //rightArm
	private final ModelRenderer leg0; //leftLeg
 	private final ModelRenderer leg1; //rightLeg

	public NewDimGolemModel()
	{
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -7.0F, 0.0F);
		body.setTextureOffset(0, 40).addBox(-9.0F, -4.0F, -6.0F, 18.0F, 14.0F, 11.0F, 0.0F, true);
		body.setTextureOffset(0, 70).addBox(-4.5F, 10.0F, -3.0F, 9.0F, 5.0F, 6.0F, 0.5F, true);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -7.0F, -2.0F);
		head.setTextureOffset(20, 27).addBox(-6.0F, -15.0F, -2.5F, 2.0F, 5.0F, 2.0F, 0.0F, true);
		head.setTextureOffset(10, 27).addBox(4.0F, -15.0F, -2.5F, 2.0F, 5.0F, 2.0F, 0.0F, true);
		head.setTextureOffset(0, 0).addBox(-4.0F, -19.0F, -5.5F, 8.0F, 15.0F, 8.0F, 0.0F, true);
		head.setTextureOffset(0, 23).addBox(-1.0F, -10.0F, -7.5F, 2.0F, 9.0F, 2.0F, 0.0F, true);

		arm0 = new ModelRenderer(this);
		arm0.setRotationPoint(0.0F, -7.0F, 0.0F);
		arm0.setTextureOffset(60, 21).addBox(9.0F, -3.5F, -3.0F, 4.0F, 30.0F, 6.0F, 0.0F, true);

		arm1 = new ModelRenderer(this);
		arm1.setRotationPoint(0.0F, -7.0F, 0.0F);
		arm1.setTextureOffset(60, 58).addBox(-13.0F, -3.5F, -3.0F, 4.0F, 30.0F, 6.0F, 0.0F, true);

		leg0 = new ModelRenderer(this);
		leg0.setRotationPoint(4.0F, 11.0F, 0.0F);
		leg0.setTextureOffset(37, 0).addBox(-2.5F, -3.0F, -3.0F, 6.0F, 16.0F, 5.0F, 0.0F, true);

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-5.0F, 11.0F, 0.0F);
		leg1.setTextureOffset(60, 0).addBox(-2.5F, -3.0F, -3.0F, 6.0F, 16.0F, 5.0F, 0.0F, false);
	}
	
	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.leg0.rotateAngleX = -1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
		this.leg1.rotateAngleX = 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
		this.leg0.rotateAngleY = 0.0F;
		this.leg1.rotateAngleY = 0.0F;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		arm0.render(matrixStack, buffer, packedLight, packedOverlay);
		arm1.render(matrixStack, buffer, packedLight, packedOverlay);
		leg0.render(matrixStack, buffer, packedLight, packedOverlay);
		leg1.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
		 /* TODO : ajouter animation des bras pendant les attaques
		 int i = entityIn.getAttackTimer();
		 if (i > 0)
		 {
		    this.ironGolemRightArm.rotateAngleX = -2.0F + 1.5F * MathHelper.func_233021_e_((float)i - partialTick, 10.0F);
		    this.ironGolemLeftArm.rotateAngleX = -2.0F + 1.5F * MathHelper.func_233021_e_((float)i - partialTick, 10.0F);
		 }
		 else
		 {
		    int j = entityIn.getHoldRoseTick();
		    if (j > 0)
		    {
		       this.ironGolemRightArm.rotateAngleX = -0.8F + 0.025F * MathHelper.func_233021_e_((float)j, 70.0F);
		       this.ironGolemLeftArm.rotateAngleX = 0.0F;
            }
		    else
		    {*/
               this.arm1.rotateAngleX = (-0.2F + 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount;
		       this.arm0.rotateAngleX = (-0.2F - 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount;
		    /*}
		 }*/
	}
	   

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}