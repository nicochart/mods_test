package fr.factionbedrock.Mixin;

import fr.factionbedrock.Registry.AerialHellItems;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(AbstractFurnaceBlockEntity.class)
public class FurnaceFuelItemMixin
{
    @Inject(method = "createFuelTimeMap", at = @At("RETURN"), cancellable = true)
    private static void addCustomFuels(CallbackInfoReturnable<Map<Item, Integer>> cir)
    {
        Map<Item, Integer> fuelMap = cir.getReturnValue();
        fuelMap.put(AerialHellItems.SKY_STICK, 200);
        cir.setReturnValue(fuelMap);
    }
}
