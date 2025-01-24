package com.example.examplemod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Orientation;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NeighborChangedTestBlock extends Block
{
    public NeighborChangedTestBlock(Properties properties) {super(properties);}

    @Override protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean movedByPiston)
    {
        super.neighborChanged(state, level, pos, neighborBlock, orientation, movedByPiston);

        if (level instanceof ServerLevel serverLevel)
        {
            //looks like orientation is always null
            String orientationString = "orientation = "+ (orientation == null ? "null" : orientation.toString() + (orientation.getFront() == null ? "" : ", front = "+orientation.getFront()));
            List<ServerPlayer> playerList = serverLevel.getServer().getPlayerList().getPlayers();
            playerList.forEach(player -> player.sendSystemMessage(Component.literal("neighborChanged at "+pos.getX()+", y="+pos.getY()+", z="+pos.getZ()+", "+orientationString)));
        }
    }
}
