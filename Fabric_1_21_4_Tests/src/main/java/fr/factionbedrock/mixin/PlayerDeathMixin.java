package fr.factionbedrock.mixin;

import fr.factionbedrock.registry.TestTrackedData;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class PlayerDeathMixin
{
    @Inject(at = @At("HEAD"), method = "copyFrom")
    private void applyOnCopyFrom(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo info)
    {
        ServerPlayerEntity newPlayer = (ServerPlayerEntity) (Object) this;

        int total_click_count = oldPlayer.getDataTracker().get(TestTrackedData.TOTAL_CLICK_COUNT);
        newPlayer.getDataTracker().set(TestTrackedData.TOTAL_CLICK_COUNT, total_click_count);
    }
}
