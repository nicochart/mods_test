package fr.factionbedrock.aerialhell.Client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.MudGolemEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

//Made by Cixon using BlockBench
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class MudGolemModel<T extends MudGolemEntity> extends EntityModel<T>
{
	public static final ModelLayerLocation MUD_GOLEM = new ModelLayerLocation(new ResourceLocation(AerialHell.MODID, "mud_golem"), "main");
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	public MudGolemModel(ModelPart root)
	{
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.left_arm = root.getChild("left_arm");
		this.right_arm = root.getChild("right_arm");
		this.left_leg = root.getChild("left_leg");
		this.right_leg = root.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -9.8F, -4.5F, 14.0F, 10.0F, 8.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 19).addBox(-10.0F, -29.0F, -5.0F, 20.0F, 12.0F, 10.0F, new CubeDeformation(-1.0F))
				.texOffs(9, 42).addBox(-7.5F, -18.0F, -2.5F, 15.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(62, 54).addBox(0.0F, -1.0F, -2.5F, 5.0F, 25.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, -3.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(62, 19).mirror().addBox(-5.0F, -1.0F, -2.5F, 5.0F, 25.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-9.0F, -3.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(18, 72).mirror().addBox(-3.5F, 0.0F, -2.5F, 6.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 11.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(18, 53).addBox(-3.5F, 0.0F, -2.5F, 6.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 11.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		this.left_arm.xRot = (-0.2F + 0.8F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
		this.right_arm.xRot = (-0.2F - 0.8F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;

		this.left_leg.xRot = -1.5F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.right_leg.xRot = 1.5F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.left_leg.yRot = 0.0F; this.right_leg.yRot = 0.0F;
	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}