package fr.factionbedrock.newdim.Block;

import java.util.function.Supplier;

import net.minecraft.block.ChestBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.ToolType;

public class NewDimChestBlock extends ChestBlock
{
    public NewDimChestBlock(Material material, float hardness, float resist, SoundType sound, int harvestlvl, ToolType tool) {
        super(Properties.create(material)
        .hardnessAndResistance(hardness,resist)
        .sound(sound)
        .harvestLevel(harvestlvl)
        .harvestTool(tool),
        (Supplier<TileEntityType<? extends ChestTileEntity>>) TileEntityType.CHEST
        );
    }
}
