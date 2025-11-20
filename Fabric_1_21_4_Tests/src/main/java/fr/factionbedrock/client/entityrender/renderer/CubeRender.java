package fr.factionbedrock.client.entityrender.renderer;

import fr.factionbedrock.FabricTest;
import fr.factionbedrock.client.entityrender.model.CubeModel;
import fr.factionbedrock.client.entityrender.model.TestModelLayers;
import fr.factionbedrock.client.entityrender.state.CubeRenderState;
import fr.factionbedrock.entity.PartEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.Identifier;

public class CubeRender<T extends HostileEntity> extends MobEntityRenderer<T, CubeRenderState, CubeModel<CubeRenderState>>
{
	private static final Identifier CUBE_TEMPLATE_TEXTURE = FabricTest.id("textures/entity/cube_template.png");

	public CubeRender(EntityRendererFactory.Context context)
	{
		super(context, new CubeModel<>(context.getPart(TestModelLayers.CUBE)), 0.5F);
	}

	@Override public CubeRenderState createRenderState() {return new CubeRenderState();}

	@Override public void updateRenderState(T entity, CubeRenderState renderState, float f)
	{
		super.updateRenderState(entity, renderState, f);
		renderState.texture = getTexture(entity);
		renderState.isHead = isHead(entity);
	}

	public boolean isHead(T entity)
	{
		return entity instanceof PartEntity partEntity && partEntity.isHead();
	}

	public Identifier getTexture(T entity)
	{
		return CUBE_TEMPLATE_TEXTURE;
	}

	@Override public Identifier getTexture(CubeRenderState renderState) {return renderState.texture;}
}