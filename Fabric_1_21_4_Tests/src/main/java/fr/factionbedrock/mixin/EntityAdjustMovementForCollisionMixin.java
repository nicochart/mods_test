package fr.factionbedrock.mixin;

import fr.factionbedrock.entity.CubeEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public class EntityAdjustMovementForCollisionMixin
{
    @Redirect(method = "adjustMovementForCollisions", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getBoundingBox()Lnet/minecraft/util/math/Box;"))
    private Box redirectBoundingBox(Entity instance)
    {
        if (instance instanceof CubeEntity cube) {return cube.getBlockCollisionBoundingBox();}

        return instance.getBoundingBox();
    }
}
