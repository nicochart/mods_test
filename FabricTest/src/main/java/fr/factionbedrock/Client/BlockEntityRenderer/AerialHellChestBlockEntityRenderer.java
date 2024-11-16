package fr.factionbedrock.Client.BlockEntityRenderer;

import fr.factionbedrock.BlockEntity.AerialHellChestBlockEntity;
import fr.factionbedrock.Client.Registry.AerialHellChestMaterials;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.block.*;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.ChestBlockEntityRenderer;
import net.minecraft.client.render.block.entity.LightmapCoordinatesRetriever;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.World;

public class AerialHellChestBlockEntityRenderer<T extends AerialHellChestBlockEntity> extends ChestBlockEntityRenderer<T>
{
	public AerialHellChestBlockEntityRenderer(BlockEntityRendererFactory.Context context) {super(context);}

	@Override public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay)
	{
		World world = entity.getWorld();
		boolean worldNotNull = world != null;
		BlockState blockState = worldNotNull ? entity.getCachedState() : Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, Direction.SOUTH);
		ChestType chestType = blockState.contains(ChestBlock.CHEST_TYPE) ? blockState.get(ChestBlock.CHEST_TYPE) : ChestType.SINGLE;
		Block block = blockState.getBlock();
		if (block instanceof AbstractChestBlock<?> abstractChestBlock)
		{
			boolean notSingle = chestType != ChestType.SINGLE;
			matrices.push();
			float f = (blockState.get(ChestBlock.FACING)).asRotation();
			matrices.translate(0.5F, 0.5F, 0.5F);
			matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-f));
			matrices.translate(-0.5F, -0.5F, -0.5F);
			DoubleBlockProperties.PropertySource<? extends ChestBlockEntity> propertySource;
			if (worldNotNull) {propertySource = abstractChestBlock.getBlockEntitySource(blockState, world, entity.getPos(), true);}
			else {propertySource = DoubleBlockProperties.PropertyRetriever::getFallback;}

			float openness = ((Float2FloatFunction)propertySource.apply(ChestBlock.getAnimationProgressRetriever(entity))).get(tickDelta);
			openness = 1.0F - openness;
			openness = 1.0F - openness * openness * openness;
			int i = ((Int2IntFunction)propertySource.apply(new LightmapCoordinatesRetriever())).applyAsInt(light);
			SpriteIdentifier spriteIdentifier = getSpriteIdentifier(chestType, AerialHellChestMaterials.AERIAL_TREE_SINGLE, AerialHellChestMaterials.AERIAL_TREE_LEFT, AerialHellChestMaterials.AERIAL_TREE_RIGHT);
			VertexConsumer vertexConsumer = spriteIdentifier.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
			if (notSingle)
			{
				if (chestType == ChestType.LEFT) {this.render(matrices, vertexConsumer, this.doubleChestLeftLid, this.doubleChestLeftLatch, this.doubleChestLeftBase, openness, i, overlay);}
				else {this.render(matrices, vertexConsumer, this.doubleChestRightLid, this.doubleChestRightLatch, this.doubleChestRightBase, openness, i, overlay);}
			}
			else {this.render(matrices, vertexConsumer, this.singleChestLid, this.singleChestLatch, this.singleChestBase, openness, i, overlay);}

			matrices.pop();
		}
	}

	private static SpriteIdentifier getSpriteIdentifier(ChestType type, SpriteIdentifier single, SpriteIdentifier left, SpriteIdentifier right)
	{
		return switch (type)
		{
			case LEFT -> left;
			case RIGHT -> right;
			case SINGLE -> single;
		};
	}
}