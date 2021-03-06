package fr.factionbedrock.newdim.Register;

import java.util.List;
import com.google.common.collect.Lists;

import fr.factionbedrock.newdim.NewDimension;
import fr.factionbedrock.newdim.TileEntity.NewDimChestTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.ChestBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisterTileEntityTypes
{
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, NewDimension.MODID);
	
	public static final RegistryObject<TileEntityType<NewDimChestTileEntity>> CHEST =
			TILE_ENTITY_TYPES.register("chest", 
							() -> TileEntityType.Builder.create(NewDimChestTileEntity::new, 
							getChests()).build(null));
	
	static Block[] getChests() 
	{
		List<Block> result = Lists.newArrayList();
		RegisterItems.ITEMS.getEntries().forEach((item) -> {
			if (item.get() instanceof BlockItem) 
			{
				Block block = ((BlockItem) item.get()).getBlock();
				if (block instanceof ChestBlock) 
				{
					result.add(block);
				}
			}
		});
		return result.toArray(new Block[] {});
	}
}
