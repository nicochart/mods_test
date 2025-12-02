package fr.factionbedrock.client.entityrender.renderer;

import fr.factionbedrock.FabricTest;
import fr.factionbedrock.client.entityrender.model.CubeModel;
import fr.factionbedrock.client.entityrender.model.TestModelLayers;
import fr.factionbedrock.client.entityrender.state.CubeRenderState;
import fr.factionbedrock.entity.PartEntity;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import org.jetbrains.annotations.NotNull;

public class CubeRender<T extends PartEntity> extends MobEntityRenderer<T, CubeRenderState, CubeModel<CubeRenderState>>
{
	private static final Identifier CUBE_TEMPLATE_TEXTURE = FabricTest.id("textures/entity/cube_template.png");

	public CubeRender(EntityRendererFactory.Context context)
	{
		super(context, new CubeModel<>(context.getPart(TestModelLayers.CUBE)), 0.0F);
		this.shadowOpacity = 0.0F;
	}

	@Override public void render(CubeRenderState cubeRenderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light)
	{
		super.render(cubeRenderState, matrixStack, vertexConsumerProvider, cubeRenderState.packedLight);
	}

	@Override public CubeRenderState createRenderState() {return new CubeRenderState();}

	@Override public void updateRenderState(T entity, CubeRenderState renderState, float f)
	{
		super.updateRenderState(entity, renderState, f);
		renderState.texture = getTexture(entity);
		renderState.isLeftArm = isLeftArm(entity);
		renderState.isRightArm = isRightArm(entity);
		renderState.isHead = isHead(entity);
		renderState.packedLight = entity.getOwner() == null ? 0 : this.getOwnerLight(entity.getOwner(), f);
	}

	//copy of net.minecraft.client.render.entity.EntityRenderer getLight but using generic Entity instead of T
	public final int getOwnerLight(@NotNull Entity owner, float tickDelta)
	{
		BlockPos blockPos = BlockPos.ofFloored(owner.getClientCameraPosVec(tickDelta));
		return LightmapTextureManager.pack(this.getOwnerBlockLight(owner, blockPos), this.getOwnerSkyLight(owner, blockPos));
	}

	//copy of net.minecraft.client.render.entity.EntityRenderer getSkyLight but using generic Entity instead of T
	protected int getOwnerSkyLight(@NotNull Entity entity, BlockPos pos)
	{
		return entity.getWorld().getLightLevel(LightType.SKY, pos);
	}

	//copy of net.minecraft.client.render.entity.EntityRenderer getBlockLight but using generic Entity instead of T
	protected int getOwnerBlockLight(@NotNull Entity entity, BlockPos pos)
	{
		return entity.isOnFire() ? 15 : entity.getWorld().getLightLevel(LightType.BLOCK, pos);
	}

	public boolean isLeftArm(T entity) {return entity.isLeftArm();}
	public boolean isRightArm(T entity) {return entity.isRightArm();}
	public boolean isHead(T entity) {return entity.isHead();}

	public Identifier getTexture(T entity)
	{
		return CUBE_TEMPLATE_TEXTURE;
	}

	@Override public Identifier getTexture(CubeRenderState renderState) {return renderState.texture;}
}