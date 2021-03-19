package fr.factionbedrock.newdim.World.Features;

import java.util.Random;

import com.mojang.serialization.Codec;

import fr.factionbedrock.newdim.Setup.Registration;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class NewDimQuicksoilAercloudFeature extends Feature<NoFeatureConfig>
{
    public NewDimQuicksoilAercloudFeature(Codec<NoFeatureConfig> codec)
    {
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
                (reader.getBlockState(pos).getBlock() == Registration.NEWDIMSTONE.get()) ||
                 reader.getBlockState(pos).getBlock() == Registration.NEWDIM_QUICKSOIL.get());
        if (doesProtrude)
        {
        	/*Quelques maths pour décider de la taille du plan de quicksoil*/
        	double Decision= Math.random();
        	int Min,Max;
        	if (Decision > 0.8) //grand (20% des cas)
        	{
        		Min=4;Max=6;
        	}
        	else //petit (80% des cas)
        	{
        		Min=3;Max=4;
        	}
        	int Sizex = Min + (int)(Math.random() * ((Max - Min) + 1));
        	int Sizez = Min + (int)(Math.random() * ((Max - Min) + 1));
        	/*Placement du quicksoil*/
            for(int x = pos.getX() - Sizex; x < pos.getX() + Sizex+1; x++) {
                for(int z = pos.getZ() - Sizez; z < pos.getZ() + Sizez+1; z++)
                {
                    BlockPos newPos = new BlockPos(x, pos.getY(), z);

                    if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < Sizex*Sizez-1)
                    {
                        reader.setBlockState(newPos, Registration.NEWDIM_QUICKSOIL_AERCLOUD.get().getDefaultState(), 1);
                    }
                }
            }
        }
        return false;
    }
}
