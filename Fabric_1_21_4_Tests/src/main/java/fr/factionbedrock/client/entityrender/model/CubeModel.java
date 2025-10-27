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
	private final ModelPart head;

	public CubeModel(ModelPart root)
	{
		super(root);
		this.head = root.getChild("head");
	}
	public static TexturedModelData createBodyLayer()
	{
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-4.0F, 16.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 16);
	}

	@Override public void setAngles(S renderState)
	{
	}

	@Override public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		head.render(matrices, vertexConsumer, packedLight, packedOverlay, tint);
	}
}