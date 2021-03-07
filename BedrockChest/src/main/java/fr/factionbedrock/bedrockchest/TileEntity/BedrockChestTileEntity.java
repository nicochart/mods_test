package fr.factionbedrock.bedrockchest.TileEntity;

import fr.factionbedrock.bedrockchest.Registry.RegisterTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.ChestTileEntity;

public class BedrockChestTileEntity extends ChestTileEntity
{
	private Block chest = Blocks.AIR;
	
	public BedrockChestTileEntity() {
		super(RegisterTileEntityTypes.CHEST.get());
	}

	public void setChest(Block chest) {
		this.chest = chest;
	}
	
	public Block getChest() {
		return chest;
	}
	
	public boolean hasChest() {
		return !chest.getDefaultState().isAir();
	}
}
