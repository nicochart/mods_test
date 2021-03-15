package fr.factionbedrock.newdim.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.newdim.Register.RegisterTags;
import fr.factionbedrock.newdim.Setup.Registration;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class NewDimQuicksoilFeature extends Feature<NoFeatureConfig> {

    public NewDimQuicksoilFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        @SuppressWarnings("deprecation")
		boolean doesProtrude = (
                (reader.getBlockState(pos.west(3)).isAir() ||
                        reader.getBlockState(pos.north(3)).isAir() ||
                        reader.getBlockState(pos.south(3)).isAir() ||
                        reader.getBlockState(pos.east(3)).isAir()) &&
                (reader.getBlockState(pos).isIn(RegisterTags.Blocks.NEWDIM_STONE) ||
                        reader.getBlockState(pos).getBlock() == Registration.NEWDIM_DIRT.get()));
        if (doesProtrude)
        {
            for(int x = pos.getX() - 3; x < pos.getX() + 4; x++) {
                for(int z = pos.getZ() - 3; z < pos.getZ() + 4; z++)
                {
                    BlockPos newPos = new BlockPos(x, pos.getY(), z);

                    if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < 12)
                    {
                        reader.setBlockState(newPos, Registration.NEWDIM_QUICKSOIL.get().getDefaultState(), 0);
                    }
                }

            }
        }
        return false;
    }
}