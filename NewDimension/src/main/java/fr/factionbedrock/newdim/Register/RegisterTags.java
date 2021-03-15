package fr.factionbedrock.newdim.Register;

import fr.factionbedrock.newdim.NewDimension;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

public class RegisterTags
{
	public static class Blocks
	{
		public static final ITag.INamedTag<Block> NEWDIM_STONE = tag("newdimstone");

		private static ITag.INamedTag<Block> tag(String name) {
			return BlockTags.makeWrapperTag(new ResourceLocation(NewDimension.MODID, name).toString());
		}
	}
}
