package fr.factionbedrock.bedrockchest.Client.Renderer;

import com.mojang.blaze3d.matrix.MatrixStack;

import fr.factionbedrock.bedrockchest.TileEntity.BedrockChestTileEntity;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;

public class BedrockChestItemTileEntityRenderer extends ItemStackTileEntityRenderer
{
	/*Called in RegisterItems, when creating "item" corresponding to block chest
	  Code Source: Better End*/
	
	private BedrockChestTileEntity chest;

	@Override
	public void func_239207_a_(ItemStack stack, TransformType p_239207_2_, MatrixStack matrixStack,
			IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay)
	{
		if (chest == null)
			chest = new BedrockChestTileEntity();
		chest.setChest(Block.getBlockFromItem(stack.getItem()));
		TileEntityRendererDispatcher.instance.renderItem(chest, matrixStack, buffer, combinedLight, combinedOverlay);
	}
}
