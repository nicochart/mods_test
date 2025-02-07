package fr.factionbedrock.Mixin;

import fr.factionbedrock.Util.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class UpdateNeighborsExceptMixin
{
    @Inject(method = "updateNeighborsExcept", at = @At("HEAD"), cancellable = true)
    private void onNeighborsUpdate(BlockPos pos, Block sourceBlock, Direction direction, CallbackInfo callbackInfo)
    {
        ServerWorld serverWorld = (ServerWorld) (Object) this;
        WorldHelper.neighborUpdate(serverWorld, pos);
    }
}
