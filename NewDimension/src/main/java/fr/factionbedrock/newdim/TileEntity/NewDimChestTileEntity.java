package fr.factionbedrock.newdim.TileEntity;

import fr.factionbedrock.newdim.Register.RegisterTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.ChestTileEntity;

public class NewDimChestTileEntity extends ChestTileEntity
{
	private Block chest = Blocks.AIR;
	
	public NewDimChestTileEntity()
	{
		super(RegisterTileEntityTypes.CHEST.get());
	}

	public void setChest(Block chest)
	{
		this.chest = chest;
	}
	
	public Block getChest()
	{
		return chest;
	}
	
	public boolean hasChest()
	{
		return !chest.getDefaultState().isAir();
	}
}
