package fr.factionbedrock.Mixin;

import fr.factionbedrock.Registry.Worldgen.AerialHellDimensions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Block.class)
public class OnBlockPlacedMixin
{
	//net.minecraft.block.Block.onPlaced
	@Inject(method = "onPlaced", at = @At("HEAD"), cancellable = true)
	private void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack, CallbackInfo callbackInfo)
	{
		if (world.isClient) {return;}

		if (world.getDimensionEntry().matchesId(AerialHellDimensions.AERIAL_HELL_DIMENSION.getValue()))
		{
			if (state.getBlock() == Blocks.TORCH || state.getBlock() == Blocks.WALL_TORCH)
			{
				if (placer.getServer() != null)
				{
					List<ServerPlayerEntity> players = placer.getServer().getPlayerManager().getPlayerList();
					players.forEach(player -> player.sendMessage(Text.literal(player.getName().getString() + " tried to place a torch in Aerial Hell at x="+pos.getX()+", y="+pos.getY()+", z="+pos.getZ()+" ...!")));
				}

				world.breakBlock(pos, true);
				callbackInfo.cancel();
			}
		}
	}
}