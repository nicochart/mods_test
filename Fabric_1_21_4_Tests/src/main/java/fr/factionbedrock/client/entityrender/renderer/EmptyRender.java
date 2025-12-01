package fr.factionbedrock.client.entityrender.renderer;

import fr.factionbedrock.entity.CubeEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.util.math.MatrixStack;

public class EmptyRender extends EntityRenderer<CubeEntity, EntityRenderState>
{
    public EmptyRender(EntityRendererFactory.Context context)
    {
        super(context);
        this.shadowRadius = 0.65F;
    }

    @Override public void render(EntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {}

    @Override public EntityRenderState createRenderState() {return new EntityRenderState();}
}
