package fr.factionbedrock.newdim.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.newdim.Setup.Registration;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class NewDimGreenAercloudFeature extends Feature<NoFeatureConfig>
{
	public NewDimGreenAercloudFeature(Codec<NoFeatureConfig> codec)
	{
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        BlockPos origin = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
        int MinH=80,MaxH=110; int yh = MinH + (int)(Math.random() * ((MaxH - MinH) + 1));
        BlockPos position = new BlockPos(origin.getX() + 8, yh, origin.getZ() + 8);

        for (int amount = 0; amount < 8; ++amount) {
            int xOffset = rand.nextInt(2);
            int zOffset = rand.nextInt(2);

            position = position.add(xOffset, 0, zOffset);
            
            for (int x = position.getX(); x < position.getX() + rand.nextInt(2) + 3; ++x) {
                for (int y = position.getY(); y < position.getY() + rand.nextInt(1) + 2; ++y) {
                    for (int z = position.getZ(); z < position.getZ() + rand.nextInt(2) + 3; ++z) {
                        BlockPos newPosition = new BlockPos(x, y, z);

                        if (reader.isAirBlock(newPosition)) {
                            if (Math.abs(x - position.getX()) + Math.abs(y - position.getY()) + Math.abs(z - position.getZ()) < 4 + rand.nextInt(2)) {
                                this.setBlockState(reader, newPosition, Registration.NEWDIM_GREEN_AERCLOUD.get().getDefaultState());
                            }
                        }
                    }
                }
            }
        }

        return false;
    }
}