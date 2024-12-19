package fr.factionbedrock.Mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(LivingEntity.class)
public class LivingJumpMixin
{
    @Inject(method = "jump", at = @At("RETURN"), cancellable = true)
    private void onLivingJump(CallbackInfo callbackInfo)
    {
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if (livingEntity.hasStatusEffect(StatusEffects.REGENERATION))
        {
            if (livingEntity.getServer() != null)
            {
                List<ServerPlayerEntity> players = livingEntity.getServer().getPlayerManager().getPlayerList();
                players.forEach(player -> player.sendMessage(Text.literal(livingEntity.getName().getString() + " jumped with Regeneration !")));
            }
        }
    }
}
