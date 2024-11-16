package fr.factionbedrock.Block;

import fr.factionbedrock.BlockEntity.AerialHellChestBlockEntity;
import fr.factionbedrock.Registry.AerialHellBlockEntities;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class AerialHellChestBlock extends ChestBlock
{
    public AerialHellChestBlock(AbstractBlock.Settings properties) {super(properties, () -> AerialHellBlockEntities.CHEST);}

    @Override public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {return new AerialHellChestBlockEntity(pos, state);}
}