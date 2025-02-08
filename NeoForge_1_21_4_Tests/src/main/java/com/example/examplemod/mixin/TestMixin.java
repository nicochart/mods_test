package com.example.examplemod.mixin;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class TestMixin
{
    @Inject(method = "getEffectiveGravity", at = @At("RETURN"), cancellable = true)
    private void onBlockBreaking(CallbackInfoReturnable<Double> callbackInfo)
    {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity.hasEffect(MobEffects.REGENERATION))
        {
            callbackInfo.setReturnValue(-0.1D);
        }
    }
}
