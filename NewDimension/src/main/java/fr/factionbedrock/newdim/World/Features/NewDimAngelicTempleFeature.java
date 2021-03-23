package fr.factionbedrock.newdim.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.newdim.NewDimension;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.*;

import java.util.Random;

public class NewDimAngelicTempleFeature extends Feature<NoFeatureConfig> {

    private static final ResourceLocation TEMPLE = new ResourceLocation(NewDimension.MODID, "angelic_temple/angelic_temple");

    public NewDimAngelicTempleFeature(Codec<NoFeatureConfig> codec) {super(codec);}
    
    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        Rotation rotation = Rotation.randomRotation(rand);
        TemplateManager templatemanager = reader.getWorld().getServer().getTemplateManager();
        Template temple = templatemanager.getTemplateDefaulted(TEMPLE);
        ChunkPos chunkpos = new ChunkPos(pos);
        MutableBoundingBox mutableboundingbox = new MutableBoundingBox(chunkpos.getXStart(), 0, chunkpos.getZStart(), chunkpos.getXEnd(), 256, chunkpos.getZEnd());
        PlacementSettings placementsettings = (new PlacementSettings()).setRotation(rotation).setBoundingBox(mutableboundingbox).setRandom(rand).addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
        BlockPos blockpos = temple.transformedSize(rotation);
        int x = blockpos.getX();
        int z = blockpos.getZ();

        /*int Min=60,Max=90;
        int HauteurBonus = Min + (int)(Math.random() * ((Max - Min) + 1));*/
        int y = reader.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, pos.getX() + x, pos.getZ() + z) + 30;

        BlockPos blockpos1 = temple.getZeroPositionWithTransform(pos.add(x, y, z), Mirror.NONE, rotation);
        temple.func_237146_a_(reader, blockpos1, blockpos1, placementsettings, rand, 4);

        IntegrityProcessor integrityprocessor = new IntegrityProcessor(0.2F);
        placementsettings.clearProcessors().addProcessor(integrityprocessor);
    	return true;
    }
}