package fr.factionbedrock.mixin;

import fr.factionbedrock.registry.TestTrackedData;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class PlayerDeathMixin
{
    @Inject(at = @At("HEAD"), method = "onDeath")
    private void applyOnDeath(DamageSource damageSource, CallbackInfo info)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;

        int previousLives = player.getDataTracker().get(TestTrackedData.LIVES);
        if (previousLives > 0 && !player.isCreative() && !player.isSpectator())
        {
            if (previousLives == 3)
            {
                player.getDataTracker().set(TestTrackedData.LIVE_REGAIN_TIME_MARKER, player.getServerWorld().getTime());
            }
            player.getDataTracker().set(TestTrackedData.LIVES, previousLives - 1);
        }
    }

    @Inject(at = @At("HEAD"), method = "copyFrom")
    private void applyOnCopyFrom(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo info)
    {
        ServerPlayerEntity newPlayer = (ServerPlayerEntity) (Object) this;

        int total_click_count = oldPlayer.getDataTracker().get(TestTrackedData.TOTAL_CLICK_COUNT);
        newPlayer.getDataTracker().set(TestTrackedData.TOTAL_CLICK_COUNT, total_click_count);
        int lives = oldPlayer.getDataTracker().get(TestTrackedData.LIVES);
        newPlayer.getDataTracker().set(TestTrackedData.LIVES, lives);
        long live_regain_timer = oldPlayer.getDataTracker().get(TestTrackedData.LIVE_REGAIN_TIME_MARKER);
        newPlayer.getDataTracker().set(TestTrackedData.LIVE_REGAIN_TIME_MARKER, live_regain_timer);
    }
}
