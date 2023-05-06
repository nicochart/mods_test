package fr.factionbedrock.aerialhell.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

//not working properly (but that's not a problem : the objective was to generate a custom feature with no feature config)
public class DanglingChainFeature extends Feature<NoneFeatureConfiguration>
{
    public DanglingChainFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

    private static enum LinkDirection{NORTH_SOUTH, WEST_EAST}

    @Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
        BlockPos blockPos = context.origin(); WorldGenLevel reader = context.level(); Random rand = context.random(); ChunkGenerator generator = context.chunkGenerator();
        boolean canGenerate = reader.getBlockState(blockPos.below()).getBlock().equals(Blocks.AIR)
                && !reader.getBlockState(blockPos).isAir()
                && hasAnyStoneBlockAbove(blockPos.north(4).west(4), reader, 10)
                && hasAnyStoneBlockAbove(blockPos.north(4).east(4), reader, 10)
                && hasAnyStoneBlockAbove(blockPos.south(4).west(4), reader, 10)
                && hasAnyStoneBlockAbove(blockPos.south(4).east(4), reader, 10);

        if (canGenerate)
        {
            BlockPos placementPos;
            int chance_malus = 0;
            placementPos = blockPos.above(5);
            LinkDirection linkDirection = (rand.nextInt(2) == 0) ? LinkDirection.NORTH_SOUTH : LinkDirection.WEST_EAST;
            generateChainLink(reader, rand, placementPos, linkDirection);
            while (rand.nextInt(10) > (0 + chance_malus) && placementPos.getY() > 20)
            {
                chance_malus+=1;
                placementPos = blockPos.below(5);
                linkDirection = (linkDirection == LinkDirection.NORTH_SOUTH) ? LinkDirection.WEST_EAST : LinkDirection.NORTH_SOUTH;
                generateChainLink(reader, rand, placementPos, linkDirection);
            }
            placementPos = blockPos.below(5);
            linkDirection = (linkDirection == LinkDirection.NORTH_SOUTH) ? LinkDirection.WEST_EAST : LinkDirection.NORTH_SOUTH;
            generateLastLink(reader, rand, placementPos, linkDirection);
            return true;
        }
        return false;
    }

    protected void generateChainLink(WorldGenLevel reader, Random rand, BlockPos blockPos, LinkDirection direction)
    {
        int i,y;
        if (direction == LinkDirection.NORTH_SOUTH)
        {
            for (i=-2; i<3; i++)
            {
                this.setBlockStateIfPossible(reader, blockPos.offset(i, 0, 0), getRandomBlockstateToPlace(rand));
                this.setBlockStateIfPossible(reader, blockPos.offset(i, -6, 0), getRandomBlockstateToPlace(rand));
            }
            for (y=-1; y>-6; y--)
            {
                this.setBlockStateIfPossible(reader, blockPos.offset(-2, y, 0), getRandomBlockstateToPlace(rand));
                this.setBlockStateIfPossible(reader, blockPos.offset(2, y, 0), getRandomBlockstateToPlace(rand));
            }
        }
        else
        {
            for (i=-2; i<3; i++)
            {
                this.setBlockStateIfPossible(reader, blockPos.offset(0, 0, i), getRandomBlockstateToPlace(rand));
                this.setBlockStateIfPossible(reader, blockPos.offset(0, -6, i), getRandomBlockstateToPlace(rand));
            }
            for (y=-1; y>-6; y--)
            {
                this.setBlockStateIfPossible(reader, blockPos.offset(0, y, -2), getRandomBlockstateToPlace(rand));
                this.setBlockStateIfPossible(reader, blockPos.offset(0, y, 2), getRandomBlockstateToPlace(rand));
            }
        }
    }

    protected void generateLastLink(WorldGenLevel reader, Random rand, BlockPos blockPos, LinkDirection direction)
    {
        int i,y;
        if (direction == LinkDirection.NORTH_SOUTH)
        {
            for (i=-2; i<3; i++)
            {
                this.setBlockStateIfPossible(reader, blockPos.offset(i, 0, 0), getRandomBlockstateToPlace(rand));
            }
            this.setBlockStateIfPossible(reader, blockPos.offset(2, -1, 0), getRandomBlockstateToPlace(rand));
            this.setBlockStateIfPossible(reader, blockPos.offset(-2, -1, 0), getRandomBlockstateToPlace(rand));
            for (y=-2; y>-6; y--)
            {
                this.setBlockStateIfPossible(reader, blockPos.offset(-2, y, 0), getRandomBlockstateToPlace(rand));
                if (rand.nextInt(3) == 0) {break;}
            }
            for (y=-1; y>-6; y--)
            {
                this.setBlockStateIfPossible(reader, blockPos.offset(2, y, 0), getRandomBlockstateToPlace(rand));
                if (rand.nextInt(3) == 0) {break;}
            }
        }
        else
        {
            for (i=-2; i<3; i++)
            {
                this.setBlockStateIfPossible(reader, blockPos.offset(0, 0, i), getRandomBlockstateToPlace(rand));
            }
            this.setBlockStateIfPossible(reader, blockPos.offset(0, -1, 2), getRandomBlockstateToPlace(rand));
            this.setBlockStateIfPossible(reader, blockPos.offset(0, -1, -2), getRandomBlockstateToPlace(rand));
            for (y=-2; y>-6; y--)
            {
                this.setBlockStateIfPossible(reader, blockPos.offset(0, y, -2), getRandomBlockstateToPlace(rand));
                if (rand.nextInt(3) == 0) {break;}
            }
            for (y=-1; y>-6; y--)
            {
                this.setBlockStateIfPossible(reader, blockPos.offset(0, y, 2), getRandomBlockstateToPlace(rand));
                if (rand.nextInt(3) == 0) {break;}
            }
        }
    }

    private BlockState getRandomBlockstateToPlace(Random rand)
    {
        return AerialHellBlocks.STELLAR_STONE.get().defaultBlockState();
    }

    private void setBlockStateIfPossible(WorldGenLevel reader, BlockPos blockPos, BlockState state)
    {
        if (this.isReplaceable(reader, blockPos)) {reader.setBlock(blockPos, state, 0);}
    }

    private boolean isReplaceable(WorldGenLevel reader, BlockPos blockPos)
    {
        return true;//atm
    }

    private boolean hasAnyStoneBlockAbove(BlockPos pos, WorldGenLevel reader, int yMaxDistance)
    {
        for (BlockPos blockpos = pos.above(); blockpos.getY() < pos.getY() + yMaxDistance; blockpos = blockpos.above())
        {
            if (!reader.getBlockState(blockpos).isAir()) {return true;}
        }
        return false;
    }
}