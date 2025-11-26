package fr.factionbedrock.client.entityrender.model;

// Made with Blockbench 5.0.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required

import fr.factionbedrock.client.entityrender.state.CubeRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class CubeModel<S extends CubeRenderState> extends EntityModel<S>
{
	private final ModelPart cube;

	public CubeModel(ModelPart root)
	{
		super(root);
		this.cube = root.getChild("cube");
	}

	public static TexturedModelData createBodyLayer()
	{
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData cube = modelPartData.addChild("cube", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 20.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 16);
	}

	@Override public void setAngles(S renderState)
	{
		this.cube.yaw = 0.0F;
		this.cube.pitch = 0.0F;
		//if (renderState.isLeftArm || renderState.isRightArm) {this.cube.yaw = renderState.bodyYaw / 57.3F;}
		if (renderState.isHead)
		{
			this.cube.yaw = renderState.yawDegrees / 57.3F;
			this.cube.pitch = renderState.pitch / 57.3F;
		}
	}

	@Override public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		cube.render(matrices, vertexConsumer, packedLight, packedOverlay, tint);
	}
}